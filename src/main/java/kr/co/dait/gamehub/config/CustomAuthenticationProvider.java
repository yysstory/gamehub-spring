package kr.co.dait.gamehub.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import kr.co.dait.gamehub.entity.User;
import kr.co.dait.gamehub.service.UserService;
import lombok.RequiredArgsConstructor;

//@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider{

	private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userEmail=(String) authentication.getPrincipal();
		String userPassword=(String) authentication.getCredentials();
		System.out.println(userPassword);

		User user = (User)userService.loadUserByUsername(userEmail);
        if (user == null) {
            throw new BadCredentialsException("가입한 아이디가 아닙니다");
        }

		if(!passwordEncoder.matches(userPassword, user.getPassword())) {
			throw new BadCredentialsException("비밀번호가 맞지 않습니다.");
		}
		
		return new UsernamePasswordAuthenticationToken(userEmail,userPassword,user.getAuthorities());
	}
    

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
