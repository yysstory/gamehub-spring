package kr.co.dait.gamehub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.dait.gamehub.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long>{
    Game findGameByGameIdAndUserUserId(Long gameId, Long userId);
    Game findByGameIdAndGameApikey(Long gameId, String gameApikey);
    Game findByGameIdAndUserUserId(Long gameId, Long userId);
    List<Game> findGameByUserUserIdOrderByRegTimeDesc(Long userId);
}
