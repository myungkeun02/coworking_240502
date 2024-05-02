package org.myungkeun.coworking240502.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder

public class MemberSignupRequest {
    private String email;
    private String nickname;
    private String password;
}
