package kr.co.dait.gamehub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameDto {
    private Long gameId;

    @NotBlank(message = "GAME NAME은 필수 값 입니다.")
    private String gameName;
    
    private String gameApikey;   
}
