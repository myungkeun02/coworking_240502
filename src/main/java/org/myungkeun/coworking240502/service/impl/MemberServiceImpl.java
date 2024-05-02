package org.myungkeun.coworking240502.service.impl;

import lombok.RequiredArgsConstructor;
import org.myungkeun.coworking240502.domain.Member;
import org.myungkeun.coworking240502.domain.Platform;
import org.myungkeun.coworking240502.dto.request.MemberSignupRequest;
import org.myungkeun.coworking240502.dto.response.IsDuplicateEmailResponse;
import org.myungkeun.coworking240502.dto.response.IsDuplicateNicknameResponse;
import org.myungkeun.coworking240502.dto.response.MemberSignupResponse;
import org.myungkeun.coworking240502.exception.badRequest.DuplicateMemberException;
import org.myungkeun.coworking240502.exception.badRequest.InvalidEmailException;
import org.myungkeun.coworking240502.exception.badRequest.InvalidNicknameException;
import org.myungkeun.coworking240502.exception.badRequest.InvalidPasswordException;
import org.myungkeun.coworking240502.repository.MemberRepository;
import org.myungkeun.coworking240502.service.MemberService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor

public class MemberServiceImpl implements MemberService {
    private static final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*[a-z])(?=.*\\d)[a-z\\d]{8,20}$");
    private static final int EMAIL_VERIFY_CODE_MAXIMUM_NUMBER = 9999;

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberSignupResponse signup(MemberSignupRequest request) {
        validatePassword(request);
        validateDuplicateMember(request);
        String encodePassword = passwordEncoder.encode(request.getPassword());

        try {
            Member member = new Member(request.getEmail(), request.getNickname(), encodePassword, Platform.EMAIL);
            Member save = memberRepository.save(member);
            MemberSignupResponse response = MemberSignupResponse.builder()
                    .id(save.getId())
                    .email(save.getEmail())
                    .nickname(save.getNickname())
                    .build();
            return response;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateMemberException();
        }
    }

    private void validateDuplicateMember(MemberSignupRequest request) {
        if (memberRepository.existsByEmailAndPlatform(request.getEmail(), Platform.EMAIL)) {
            throw new DuplicateMemberException();
        }
    }

    private void validatePassword(MemberSignupRequest request) {
        if (!PASSWORD_REGEX.matcher(request.getPassword()).matches()) {
            throw new InvalidPasswordException();
        }
    }

    @Override
    public IsDuplicateEmailResponse isDuplicateEmail(String email) {
        validateEmail(email);
        boolean isPresent = memberRepository.existsByEmailAndPlatform(email, Platform.EMAIL);
        return new IsDuplicateEmailResponse(isPresent);
    }

    private void validateEmail(String email) {
        if (email.isBlank()) {
            throw new InvalidEmailException();
        }
    }

    @Override
    public IsDuplicateNicknameResponse isDuplicateNickname(String nickname) {
        validateNickname(nickname);
        boolean isPresent = memberRepository.existsByNickname(nickname);
        return new IsDuplicateNicknameResponse(isPresent);
    }

    private void validateNickname(String nickname) {
        if (nickname.isBlank()) {
            throw new InvalidNicknameException();
        }
    }
}
