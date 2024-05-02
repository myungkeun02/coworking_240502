package org.myungkeun.coworking240502.repository;

import org.myungkeun.coworking240502.domain.Member;
import org.myungkeun.coworking240502.domain.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmailAndPlatform(String email, Platform platform);

    boolean existsByEmailAndPlatform(String email, Platform platform);

    boolean existsByNickname(String nickname);
}
