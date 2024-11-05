package inhatc.cse.sangsushop.member.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    
    @NotBlank(message = "이름은 필수 항목입니다.")    // 이거면 무조건 데이터가 입력되야함
    private String name;

    @NotBlank(message = "이메일은 필수 항목입니다.")   
    @Email(message = "이메일 형식으로 입력하세요.")
    private String email;
    
    @Length(min=3, max=16, message = "비밀번호는 3~16자 입니다")
    private String password;

    @NotBlank(message = "주소는 필수 항목입니다.")
    private String address;
}
