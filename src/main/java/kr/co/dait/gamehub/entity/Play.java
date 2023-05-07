package kr.co.dait.gamehub.entity;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="TB_PLAY")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Play extends BaseTime{
    @Id
    @Column(name="PLAY_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playId;

    @Column(name="PLAYER_NAME")
    private String playerName;

    @Lob
    @Column(name="PLAYER_COMMENT")
    private String playerComment;

    @Column(name="PLAY_SCORE")
    private BigInteger playScore;

    @Column(name="PLAY_TIME")
    private Long playTime;

    @Column(name="PLAY_UUID")
    private String playUuid;

    @Lob
    @Column(name="ETC_TEXT")
    private String etcText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_ID")
    private Game game;
}
