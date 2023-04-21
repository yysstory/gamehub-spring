package kr.co.dait.gamehub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    // @Autowired
    // MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http.formLogin()
        //         .loginPage("/members/login")
        //         .defaultSuccessUrl("/")
        //         .usernameParameter("email")
        //         .failureUrl("/members/login/error")
        //         .and()
        //         .logout()
        //         .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
        //         .logoutSuccessUrl("/")
        // ;

        //인증 
        //http.formLogin();
        http.formLogin().loginProcessingUrl("/user/login");
        
        //인가
        http.csrf().disable();
        http.authorizeRequests().mvcMatchers("/user/**").authenticated();
        http.authorizeRequests().anyRequest().permitAll();
        
        // http.authorizeRequests()
        //         .mvcMatchers("/css/**", "/js/**", "/img/**").permitAll()
        //         .mvcMatchers("/", "/members/**", "/item/**", "/images/**").permitAll()
        //         .mvcMatchers("/admin/**").hasRole("ADMIN")
        //         .anyRequest().authenticated()
        // ;

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
