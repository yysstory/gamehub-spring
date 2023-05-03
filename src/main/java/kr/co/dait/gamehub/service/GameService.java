package kr.co.dait.gamehub.service;


import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.dait.gamehub.dto.GameDto;
import kr.co.dait.gamehub.entity.Game;
import kr.co.dait.gamehub.entity.User;
import kr.co.dait.gamehub.repository.GameRepository;
import kr.co.dait.gamehub.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class GameService {
    
    private final GameRepository gameRepository;



    public List<GameDto> getGameList(User user){
        return ModelMapperUtil.mapList(gameRepository.findGameByUserUserIdOrderByRegTimeDesc(user.getUserId()), GameDto.class);
    }

    public GameDto getGameDetail(GameDto gameDto, User user) {
        Game resultGame = gameRepository.findGameByGameIdAndUserUserId(gameDto.getGameId(),user.getUserId());
        GameDto resultGameDto = ModelMapperUtil.map(resultGame, GameDto.class);
        return resultGameDto;
    }


    public GameDto addGame(@Valid GameDto gameDto, User user) throws Exception{
        if(user==null){
            throw new Exception("user 정보가 존재하지 않습니다");
        }
        gameDto.setGameApikey(Sha512DigestUtils.shaHex(user.getUserEmail()+System.currentTimeMillis()));
        Game game = ModelMapperUtil.map(gameDto, Game.class);
        game.setUser(user);
        gameRepository.save(game);
        return ModelMapperUtil.map(game, GameDto.class);
    }
}