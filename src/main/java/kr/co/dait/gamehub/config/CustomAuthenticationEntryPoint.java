package kr.co.dait.gamehub.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.dait.gamehub.constant.ResponseCode;
import kr.co.dait.gamehub.util.ResponseData;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        ObjectMapper mapper = new ObjectMapper();

        ResponseData responseData = new ResponseData(ResponseCode.UNAUTHORIZED_ERROR);
        responseData.setMessage(authException.getMessage());
       
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(mapper.writeValueAsString(responseData));
        response.getWriter().flush();    
        
    }
    
}
