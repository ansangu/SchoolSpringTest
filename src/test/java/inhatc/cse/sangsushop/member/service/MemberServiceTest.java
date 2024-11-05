package inhatc.cse.sangsushop.member.service;

import inhatc.cse.sangsushop.member.dto.MemberDto;
import inhatc.cse.sangsushop.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
//Transactional
class MemberServiceTest {

    @Autowired  // 따로 new 뭐시기로 생성안해도 자동으로 올라옴
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberService memberService;
    
    private Member createMember() {
        MemberDto memberDto = MemberDto.builder()   // dto격식에 맞게 멤버생성
                .name("홍길동")
                .email("hong@email.com7")
                .address("인천")
                .password("1111")
                .build();

        System.out.println(memberDto);

        // 컨트롤+1로 자동으로 지역변수 생성
        // 알트 쉬프트 L도 됨
        Member member = Member.createMember(memberDto, passwordEncoder);// 해당정보의 멤버를 생성
        
        System.out.println(member); 
        // 위랑 비교하면 id는 기본 0이되고 password도 인코딩으로 암호화된걸 알수있음
        // 다만 아직 DTO상태라 DB에 들어간건 아님

        return member;
    }


    @Test
    @DisplayName("사용자 등록테스트")
    void saveMember() {
        Member member = createMember();// 컨트롤+1로 간단히 메소드 만들수있음
        // 멤버를 만들었으니 이제 DB에 넣는 작업

        Member saveMember = memberService.saveMember(member);
        System.out.println(saveMember);

        assertEquals(member.getEmail(), saveMember.getEmail()); // 값이 같아야 통과시키는 유니테스트

    }


}