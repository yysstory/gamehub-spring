package kr.co.dait.gamehub.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.co.dait.gamehub.constant.ResponseCode;
import kr.co.dait.gamehub.dto.GameDto;
import kr.co.dait.gamehub.entity.User;
import kr.co.dait.gamehub.service.GameService;
import kr.co.dait.gamehub.util.ResponseData;
import lombok.RequiredArgsConstructor;


@RequestMapping("/api/web")
@RestController
@RequiredArgsConstructor
public class GameController {

    //private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final GameService gameService;


    @GetMapping(value="/game")
    public ResponseData getGame(GameDto gameDto, @AuthenticationPrincipal User user) throws Exception {

        if(user == null){
            throw new Exception("유저 정보가 없습니다.");
        }

        Object resultGameDto = null;
        if(gameDto.getGameId() == null){
            resultGameDto = gameService.getGameList(user);
        }else{
            resultGameDto = gameService.getGameDetail(gameDto,user);
        }
        return new ResponseData(ResponseCode.OK, resultGameDto);
    }

    @PostMapping(value="/game")
    public ResponseData addGame(@Valid GameDto gameDto, @AuthenticationPrincipal User user) throws Exception{
        GameDto returnGameDto = gameService.addGame(gameDto,user);
        return new ResponseData(ResponseCode.OK, returnGameDto);
    }
}
