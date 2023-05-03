package kr.co.dait.gamehub.dto;

import lombok.Data;
@Data
public class UserGameDto {

    private Long gameId;
    private String gameName;
    private String gameApikey;
    private Long userId;

}
