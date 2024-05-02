package org.myungkeun.coworking240502.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String nickname;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column
    private Platform platform;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    public Member(String email, String nickname, String password, Platform platform) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.platform = platform;
        this.status = Status.ACTIVE;
    }
}
