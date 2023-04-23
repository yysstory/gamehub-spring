package kr.co.dait.gamehub.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            org.springframework.security.core.AuthenticationException exception) throws IOException, ServletException {
                ObjectMapper mapper = new ObjectMapper();

                // ResponseDataDTO responseDataDTO = new ResponseDataDTO();
                // responseDataDTO.setCode(ResponseDataCode.ERROR);
                // responseDataDTO.setStatus(ResponseDataCode.ERROR);
                // responseDataDTO.setMessage(exception.getMessage());
                System.out.println("로그인 실패");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpServletResponse.SC_OK);
                // response.getWriter().println(mapper.writeValueAsString(responseDataDTO));
                response.getWriter().flush();    
            }


}
