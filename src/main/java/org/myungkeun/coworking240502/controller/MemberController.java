package org.myungkeun.coworking240502.controller;

import lombok.RequiredArgsConstructor;
import org.myungkeun.coworking240502.dto.request.MemberSignupRequest;
import org.myungkeun.coworking240502.dto.response.BaseResponse;
import org.myungkeun.coworking240502.dto.response.IsDuplicateEmailResponse;
import org.myungkeun.coworking240502.dto.response.IsDuplicateNicknameResponse;
import org.myungkeun.coworking240502.dto.response.MemberSignupResponse;
import org.myungkeun.coworking240502.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")

public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<MemberSignupResponse>> signup(
            @RequestBody MemberSignupRequest request
    ) {
        MemberSignupResponse response = memberService.signup(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponse
                        .<MemberSignupResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .data(response)
                        .build());
    }

    @GetMapping("/check-duplicate/email")
    public ResponseEntity<BaseResponse<IsDuplicateEmailResponse>> checkDuplicateEmail(
            @RequestParam String email
    ) {
        IsDuplicateEmailResponse response = memberService.isDuplicateEmail(email);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse
                        .<IsDuplicateEmailResponse>builder()
                        .data(response)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @GetMapping("/check-duplicate/nickname")
    public ResponseEntity<BaseResponse<IsDuplicateNicknameResponse>> checkDuplicateNickname(
            @RequestParam String nickname
    ) {
        IsDuplicateNicknameResponse response = memberService.isDuplicateNickname(nickname);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse
                        .<IsDuplicateNicknameResponse>builder()
                        .data(response)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }
}
