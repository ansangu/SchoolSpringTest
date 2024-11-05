package inhatc.cse.sangsushop.member.service;

import inhatc.cse.sangsushop.member.entity.Member;
import inhatc.cse.sangsushop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional      // 트랜젝션 기능으로, 실행중 오류같은게 생기면 오류발생전으로 롤백
@RequiredArgsConstructor    //아래 변수를 인자가 있는 생성자로 올림, 무조건 final 적어야함 그럼 auto 와이어드 같아짐
@Slf4j
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 알트+인설트로 테스트 만듬

    public Member saveMember(Member member) {   // 저장하기
        validateDuplicateMember(member);    //중복확인
        return memberRepository.save(member);   //DB넣기
    }

    // private라 노출안됨
    private void validateDuplicateMember(Member member) {   // 중복체크
        Optional<Member> mem = memberRepository.findByEmail(member.getEmail()); //해당 이메일이 있는가
        
        // 존재한다면
        if(mem.isPresent())  {  // 중복이라면
            Member m = mem.get();
            throw new IllegalStateException("이미 가입된 회원 입니다.");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        /*
        // 아래에 한줄과 같은기능
        Optional<Member> optMember = memberRepository.findByEmail(email); // 컨트롤1로 지역변수만들자
        
        // 사용자가 존재한다면 계정이 있다는거니까 로그인 되도록
        if(optMember.isPresent()) {
            Member member = optMember.get();
        }
        else {
            throw new UsernameNotFoundException("해당사용자가 존재하지 않습니다."+email);
        }
        */

        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("해당사용자가 존재하지 않습니다."+email));  // 예외가 발생하면 예외처리

        // System.out.println(member); // 검토때는 몰라도 상시이러면 출력이 너무 어지러워 짐
        log.info(member.toString());    // 로그 출력
        

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())  // 이 유저시큐리티에는 이 3개는 필수임, 그리고 role은 enum으로 문자가 아니라 toString 씀
                .build();
    }
}
