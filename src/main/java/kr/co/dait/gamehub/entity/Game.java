package kr.co.dait.gamehub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="TB_GAME")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game extends BaseTime{
    
    @Id
    @Column(name="GAME_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameId;

    @Column(name="GAME_NAME")
    private String gameName;

    @Column(name="GAME_APIKEY")
    private String gameApikey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
}
