package kr.co.dait.gamehub.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.dait.gamehub.constant.ResponseCode;
import kr.co.dait.gamehub.entity.User;
import kr.co.dait.gamehub.util.ResponseData;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
        ObjectMapper mapper = new ObjectMapper();
        ResponseData responseData = new ResponseData(ResponseCode.LOGIN_SUCCESS);
        User user = (User) authentication.getPrincipal();
        responseData.setData(User.builder().userEmail(user.getUserEmail()).build());
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(mapper.writeValueAsString(responseData));
        response.getWriter().flush();
    }

}
