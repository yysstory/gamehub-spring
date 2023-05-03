package kr.co.dait.gamehub.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.dait.gamehub.entity.Play;

public interface PlayRepository extends JpaRepository<Play, Long>{
    List<Play> findPlayByGameGameId(String gameName);
    List<Play> findPlayByGameGameIdAndGameGameApikeyOrderByPlayScoreDesc(Long gameId, String gameApikey);
    List<Play> findTop100ByGameGameIdAndGameGameApikeyAndRegTimeGreaterThanEqualOrderByPlayScoreDescRegTimeDesc(Long gameId, String gameApikey,LocalDateTime startDatetime);
    List<Play> findPlayByGameGameIdAndGameGameApikeyAndRegTimeBetweenOrderByPlayScoreDesc(Long id, String gameApikey,LocalDateTime startDatetime,LocalDateTime endDatetime);
}
