package org.myungkeun.coworking240502.service;

import org.myungkeun.coworking240502.dto.request.MemberSignupRequest;
import org.myungkeun.coworking240502.dto.response.IsDuplicateEmailResponse;
import org.myungkeun.coworking240502.dto.response.IsDuplicateNicknameResponse;
import org.myungkeun.coworking240502.dto.response.MemberSignupResponse;

public interface MemberService {
    MemberSignupResponse signup(MemberSignupRequest request);

    IsDuplicateEmailResponse isDuplicateEmail(String email);

    IsDuplicateNicknameResponse isDuplicateNickname(String nickname);
}
