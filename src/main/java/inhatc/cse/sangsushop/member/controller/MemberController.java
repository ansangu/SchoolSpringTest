package inhatc.cse.sangsushop.member.controller;

import inhatc.cse.sangsushop.member.dto.MemberDto;
import inhatc.cse.sangsushop.member.entity.Member;
import inhatc.cse.sangsushop.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Security;

@Controller
@RequiredArgsConstructor //매개변수를 가진 생성자를 자동으로 만듬
@RequestMapping("/member")  // 전제로 해당주소 들어감
public class MemberController {

    @Autowired
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디와 패스워드 확인하세요");
        return "member/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // 현재 로그인된 사용자의 인증정보
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        
        return "redirect:/";    // 로그아웃이니 첫페이지로
    }

    
    @PostMapping("/add")    // post방식으로 날림
    public String memberNew(@Valid MemberDto memberDto, 
                            BindingResult bindingResult,
                            Model model) {   // @Valid로 멤버dto의 조건들을 체크하게 됨, 바인딩 리설트로 결과받음
        
        if (bindingResult.hasErrors()) {    // 입력할떄 DTO조건에 맞지 않으면
            return "member/add";    // 이 주소로 이동시킴
        }

        try {
            Member member = Member.createMember(memberDto, passwordEncoder);
            memberService.saveMember(member);   // 여기서 중복체크
        } catch (IllegalStateException e) { // 저장 시도하다 실패시
            model.addAttribute("errorMessage", e.getMessage());
            return "member/add";
        }
        
        
        return "redirect:/";    // 루트페이지로 이동
    }


    @GetMapping("/add")  //해당 url들어오면 /member/add"
    public String MemberAdd(Model model) {
        model.addAttribute("memberDto", new Member());  // 빈 객체 생성
        return "member/add";    // 이곳으로 이동
    }


    
}
