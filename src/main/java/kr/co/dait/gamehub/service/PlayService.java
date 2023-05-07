package kr.co.dait.gamehub.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.dait.gamehub.dto.GamePlayDto;
import kr.co.dait.gamehub.dto.PlayDto;
import kr.co.dait.gamehub.entity.Game;
import kr.co.dait.gamehub.entity.Play;
import kr.co.dait.gamehub.repository.GameRepository;
import kr.co.dait.gamehub.repository.PlayRepository;
import kr.co.dait.gamehub.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayService {
    private final PlayRepository playRepository;
    private final GameRepository gameRepository;

    public void addPlay(GamePlayDto gamePlayDto) throws Exception {

        Game game = gameRepository.findByGameIdAndGameApikey(gamePlayDto.getGameId(),gamePlayDto.getGameApikey());
        if(game != null){
            Play play = ModelMapperUtil.map(gamePlayDto, Play.class);
            play.setGame(game);
            playRepository.save(play);
        }else{
            throw new Exception("게임 정보가 일치하지 않습니다. GAME ID와 APIKEY를 확인해주세요.");
        }
    }

    public List<PlayDto> getPlay(GamePlayDto gameDto) throws Exception {
        if(gameDto.getPlayMinusDays()==null){
            gameDto.setPlayMinusDays(7L);
        }
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now().minusDays(gameDto.getPlayMinusDays()), LocalTime.of(0,0,0));//어제
        //LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));

        List<Play> plays = playRepository.findTop100ByGameGameIdAndGameGameApikeyAndRegTimeGreaterThanEqualOrderByPlayScoreDescRegTimeDesc(gameDto.getGameId(), gameDto.getGameApikey(), startDatetime);
        return ModelMapperUtil.mapList(plays, PlayDto.class);
    }


}

