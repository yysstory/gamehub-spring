package kr.co.dait.gamehub.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();

        System.out.println(authentication.getName());
        System.out.println(authentication.getCredentials());

        // ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        // responseDataDTO.setCode(ResponseDataCode.SUCCESS);
        // responseDataDTO.setStatus(ResponseDataCode.SUCCESS);
        System.out.println("로그인성공");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        // response.getWriter().print(mapper.writeValueAsString(responseDataDTO));
        response.getWriter().flush();
    }

}
