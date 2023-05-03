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
    
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //로그인
        http.formLogin(login -> login
                .loginProcessingUrl("/api/web/login") 
                .usernameParameter("userEmail") 
                .passwordParameter("userPassword") 
                .successHandler(customAuthenticationSuccessHandler) 
                .failureHandler(customAuthenticationFailureHandler));
        //로그아웃
        http.logout(logout -> logout.logoutSuccessHandler(customLogoutSuccessHandler) 
                .logoutUrl("/api/web/logout") 
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")); 

        //인증제한
        http.authorizeHttpRequests().mvcMatchers("/api/web/game/**").authenticated(); 
        //그외 전부 허용
        http.authorizeHttpRequests().anyRequest().permitAll();
        http.exceptionHandling(handling -> handling
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler));

        //csrf 인증 제거
        http.csrf(csrf->csrf.disable());
        //cors 제한 제거
        http.cors(cors->cors.disable());
        //xss basic 방어(추후 lucy 설정)
        http.headers(headers -> headers.xssProtection());

        return http.build();
    }


    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
