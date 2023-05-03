package kr.co.dait.gamehub.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.dait.gamehub.constant.ResponseCode;
import kr.co.dait.gamehub.dto.UserDto;
import kr.co.dait.gamehub.entity.User;
import kr.co.dait.gamehub.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
public class UserControllerTest {

    @Autowired
    private UserService userService;
    @Autowired
    private MockMvc mockMvc; 

    //로그인 처리
    public User userLogin(String userEmail, String userPassword) throws Exception{
        UserDto userDto = UserDto.builder().userEmail(userEmail).userPassword(userPassword).build();
        userService.addUser(userDto);
        return userService.findByUserEmail(userEmail);
    }
 
    @Test
    @DisplayName("유저 회원가입 성공 테스트")
    public void userJoinSuccessTest() throws Exception{
        mockMvc.perform(
            post("/api/web/join")
            .param("userEmail","test1@gmail.com")
            .param("userPassword","password")
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value(ResponseCode.OK.getCode()))
        .andExpect(jsonPath("$.status").value(ResponseCode.OK.getStatus()))
        ;
    }

    @Test
    @DisplayName("유저 회원가입 실패 테스트")
    public void userJoinFailTest() throws Exception{
        mockMvc.perform(
            post("/api/web/join")
            .param("userEmail","test1")
            .param("userPassword","password")
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value(ResponseCode.INTERNAL_SERVER_ERROR.getCode()))
        ;
    }
    
    @Test
    @DisplayName("유저 인증 실패 테스트")
    public void authFailTest() throws Exception {
        mockMvc.perform(
            post("/api/web/game")
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value(ResponseCode.UNAUTHORIZED.getCode()))
        .andExpect(jsonPath("$.status").value(ResponseCode.UNAUTHORIZED.getStatus()))
        ;
    }
    
    @Test
    @DisplayName("유저 인증 성공 테스트")
    public void authSuccessTest() throws Exception {
        User user = this.userLogin("test@gmail.com","test");
        mockMvc.perform(
            post("/api/web/game").param("gameName", "testGame").with(user(user))
        )
        .andDo(print())
        .andExpect(status().isOk())
        ;
    }





    // @Test
    // @DisplayName("유저 로그인 성공 테스트")
    // public void userLoginTest() throws Exception{        
    //     String userEmail = "fullstack@kakao.com";
    //     String userPassword = "alwjr425";
    //     UserDto userDto = this.addUser(userEmail, userPassword);
    //     mockMvc.perform(formLogin()
    //     .loginProcessingUrl("/web/login")
    //     .userParameter("userEmail").passwordParam("userPassword")
    //     .user(userEmail).password(userPassword))        
    //     .andDo(print())
    //     ;
    //     //.andExpect(authenticated().withRoles("USER"));
    // }


    // @Test
    // @DisplayName("유저 로그인 성공 테스트2")
    // public void userLoginTest2() throws Exception{        
    //     String userEmail = "fullstack@kakao.com";
    //     String userPassword = "alwjr425";
    //     UserDto userDto = this.addUser(userEmail, userPassword);
    //     mockMvc.perform(
    //         post("/web/login")
    //         .param("userEmail",userEmail)
    //         .param("userPassword",userPassword)
    //         )
    //     .andDo(print())
    //     .andExpect(status().isOk())
    //     ;

    // }
}
