package kr.co.dait.gamehub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.co.dait.gamehub.constant.ResponseCode;
import kr.co.dait.gamehub.dto.GamePlayDto;
import kr.co.dait.gamehub.dto.PlayDto;
import kr.co.dait.gamehub.service.PlayService;
import kr.co.dait.gamehub.util.ResponseData;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/play")
@RestController
@RequiredArgsConstructor
public class PlayController {
    
    private final PlayService playService;
    //private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping()
    public ResponseData addPlay(@Valid GamePlayDto gamePlayDto) throws Exception {
        playService.addPlay(gamePlayDto);
        return new ResponseData(ResponseCode.OK,gamePlayDto) ;
    }

    @GetMapping()
    public ResponseData getPaly(@Valid GamePlayDto gamePlayDto) throws Exception {
        List<PlayDto> playList = playService.getPlay(gamePlayDto);
        return new ResponseData(ResponseCode.OK,playList) ;
    }
       
}
