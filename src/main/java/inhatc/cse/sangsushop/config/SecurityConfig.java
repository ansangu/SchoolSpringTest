package inhatc.cse.sangsushop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.HttpSecurityDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.Security;

@Configuration  // 설정파일
@EnableWebSecurity  // 보안
public class SecurityConfig {
    // 보안관련은 다 config에 넣음

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // 로그인 무시하고 들어가는 거
        //http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin(form -> form
                .loginPage("/member/login") // 로그인 페이지 위치
                .defaultSuccessUrl("/") // 성공하면 이 위치 이동
                .failureUrl("/member/login/error")  // 실패시 이동
                .usernameParameter("email")
                .passwordParameter("password")  // html에 pw로 써서 했갈릴때 이걸로 통일시킴
                .permitAll()    // 권한이 없으면 들어오지 못하게함
        
        );

        http.logout(Customizer.withDefaults());
        
        // 접근 가능한 페이지와 아닌 페이지 나누기
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/", "/member/**").permitAll()    // 모든사용자는 루트페이지는 접근허용, 멤버로 시작하는 에도
                .requestMatchers("/css/**").permitAll()     // CSS에 대해서도 모두 허용
                .requestMatchers("/admin/**").hasRole("ADMIN")   // 관리자 페이지는 role이 ADMIN인것만 허용
                .anyRequest().authenticated()
        );
        
        // 권한없으면 거부되니 발생될때 상황
        http.exceptionHandling(exception -> exception
                //.accessDeniedPage() // 대충 접근실패했을떄 이동위치
                .authenticationEntryPoint(new CustomauthenticationEntryPoint()))
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {  // 비밀번호 헤시처럼 암호화
        return new BCryptPasswordEncoder();
    }
}
