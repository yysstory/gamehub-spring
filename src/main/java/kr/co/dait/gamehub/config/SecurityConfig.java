package kr.co.dait.gamehub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    // @Autowired
    // MemberService memberService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //인증설정
        http.formLogin()
        .loginPage("/") //인증이 필요한 곳에서 인증정보가 없을때 이동
        .loginProcessingUrl("/user/login") //로그인 처리 경로
        .usernameParameter("userName") //id파라미터
        .passwordParameter("userPassword") //pw파라미터
        .successHandler(customAuthenticationSuccessHandler) // 로그인 성공 시 실행
        .failureHandler(customAuthenticationFailureHandler) // 로그인 실패 시 실행
        .and()
        .logout().logoutSuccessHandler(customLogoutSuccessHandler) //로그아웃 성공 시 실행
        .logoutUrl("/user/logout") //로그아웃 처리 경로
        .invalidateHttpSession(true).deleteCookies("JSESSIONID"); //세션날라고 쿠키날리고

        
        //http.authorizeRequests().mvcMatchers("/user/**").authenticated(); //user 관련 
        http.authorizeRequests().anyRequest().permitAll();
        
        http.csrf().disable().cors().disable();

        //예외처리 
        // http.exceptionHandling()
        //         .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        // ;


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
