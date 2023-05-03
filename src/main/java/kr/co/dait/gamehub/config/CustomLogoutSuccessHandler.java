package kr.co.dait.gamehub.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.dait.gamehub.constant.ResponseCode;
import kr.co.dait.gamehub.util.ResponseData;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

                ObjectMapper mapper = new ObjectMapper();
                ResponseData responseDataDto = new ResponseData(ResponseCode.LOGOUT_SUCCESS);

                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().print(mapper.writeValueAsString(responseDataDto));
                response.getWriter().flush();
    }
    
}
