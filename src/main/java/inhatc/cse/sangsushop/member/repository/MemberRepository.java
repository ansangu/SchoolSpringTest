package inhatc.cse.sangsushop.member.repository;

import inhatc.cse.sangsushop.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);     // 반환이 있을 수 도 있지만 없을 수 도 있으니 옵셔널 사용


}
