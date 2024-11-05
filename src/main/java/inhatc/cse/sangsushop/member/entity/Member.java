package inhatc.cse.sangsushop.member.entity;


import inhatc.cse.sangsushop.member.constant.Role;
import inhatc.cse.sangsushop.member.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 200, nullable = true)
    private String address;

    @Enumerated(EnumType.STRING)    // 문자로 들어간다, 실제DB에선 문잔 아니지만 스프링에선 스트링이 편하니까
    private Role role;


    public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .address(memberDto.getAddress())
                .password(passwordEncoder.encode(memberDto.getPassword()))  // 비번 암호화
                .role(Role.USER)    // 열거 타입이니 열거형으로 집어넣어야 함
                .build();

        return member;
    }

}
