package kr.co.dait.gamehub.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;
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
import kr.co.dait.gamehub.dto.GamePlayDto;
import kr.co.dait.gamehub.dto.UserDto;
import kr.co.dait.gamehub.entity.User;
import kr.co.dait.gamehub.service.GameService;
import kr.co.dait.gamehub.service.PlayService;
import kr.co.dait.gamehub.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
public class PlayControllerTest {
    
    
    @Autowired
    private MockMvc mockMvc; 
    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;
    @Autowired
    private PlayService playService;

    //로그인 처리
    public User userLogin(String userEmail, String userPassword) throws Exception{
        UserDto userDto = UserDto.builder().userEmail(userEmail).userPassword(userPassword).build();
        userService.addUser(userDto);
        return userService.findByUserEmail(userEmail);
    }

    @Test
    @DisplayName("플레이 가져오기 테스트")
    public void getPlayListTest() throws Exception {
        //유저 생성
        User user = this.userLogin("test3@gmail.com","test");
        //게임 저장
        GameDto gameDto = GameDto.builder().gameName("게임1").build();
        gameService.addGame(gameDto, user);
        //플레이 저장
        List<GameDto> gameList = gameService.getGameList(user);
        Long gameId = gameList.get(0).getGameId();
        String gameApikey =  gameList.get(0).getGameApikey();
        GamePlayDto gamePlayDto = GamePlayDto.builder().gameId(gameId).gameApikey(gameApikey).playerName("yun").playScore(BigInteger.valueOf(100)).build();
        playService.addPlay(gamePlayDto);

        mockMvc.perform(
            get("/api/play")
            .param("gameId", gameId.toString())
            .param("gameApikey", gameApikey)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value(ResponseCode.OK.getCode()))
        .andExpect(jsonPath("$.status").value(ResponseCode.OK.getStatus()))
        ;
    }

   
    @Test
    @DisplayName("플레이 저장하기 테스트")
    public void addPlayTest() throws Exception {
        //유저 생성
        User user = this.userLogin("test5@gmail.com","test");
        //게임 저장
        GameDto gameDto = GameDto.builder().gameName("게임1").build();
        gameService.addGame(gameDto, user);
        //플레이 저장
        List<GameDto> gameList = gameService.getGameList(user);
        Long gameId = gameList.get(0).getGameId();
        String gameApikey =  gameList.get(0).getGameApikey();


        mockMvc.perform(
            post("/api/play")
            .param("gameId", gameId.toString())
            .param("gameApikey", gameApikey)
            .param("playerName", "yun")
            .param("playScore", "100")
            .param("playerComment", "야호")
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value(ResponseCode.OK.getCode()))
        .andExpect(jsonPath("$.status").value(ResponseCode.OK.getStatus()))
        ;
    }
}
