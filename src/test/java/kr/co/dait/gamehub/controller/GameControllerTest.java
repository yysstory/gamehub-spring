package kr.co.dait.gamehub.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.dait.gamehub.constant.ResponseCode;
import kr.co.dait.gamehub.dto.GameDto;
import kr.co.dait.gamehub.dto.UserDto;
import kr.co.dait.gamehub.entity.User;
import kr.co.dait.gamehub.service.GameService;
import kr.co.dait.gamehub.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
public class GameControllerTest {
    
    @Autowired
    private MockMvc mockMvc; 
    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;

    //로그인 처리
    public User userLogin(String userEmail, String userPassword) throws Exception{
        UserDto userDto = UserDto.builder().userEmail(userEmail).userPassword(userPassword).build();
        userService.addUser(userDto);
        return userService.findByUserEmail(userEmail);
    }

    @Test
    @DisplayName("게임리스트 불러오기 테스트")
    public void getGameListTest() throws Exception {
        User user = this.userLogin("test7@gmail.com","test");
        GameDto gameDto = GameDto.builder().gameName("게임3").build();
        gameService.addGame(gameDto, user);

        mockMvc.perform(
            get("/api/web/game").with(user(user))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value(ResponseCode.OK.getCode()))
        .andExpect(jsonPath("$.status").value(ResponseCode.OK.getStatus()))
        .andExpect(jsonPath("$.data").isNotEmpty())
        ;
    }

    @Test
    @DisplayName("게임 상세 불러오기 테스트")
    public void getGameTest() throws Exception {
        User user = this.userLogin("test8@gmail.com","test");
        GameDto gameDto = GameDto.builder().gameName("게임2").build();
        gameService.addGame(gameDto, user);
        List<GameDto> gameList = gameService.getGameList(user);

        mockMvc.perform(
            get("/api/web/game").with(user(user))
            .param("gameId", gameList.get(0).getGameId().toString())
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value(ResponseCode.OK.getCode()))
        .andExpect(jsonPath("$.status").value(ResponseCode.OK.getStatus()))
        .andExpect(jsonPath("$.data").isNotEmpty())
        ;
    }
    
    @Test
    @DisplayName("게임 저장하기 테스트")
    public void addGameTest() throws Exception {
        User user = this.userLogin("test9@gmail.com","test");

        mockMvc.perform(
            get("/api/web/game").with(user(user))
            .param("gameName", "게임1")
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value(ResponseCode.OK.getCode()))
        .andExpect(jsonPath("$.status").value(ResponseCode.OK.getStatus()))
        ;
    }

}
