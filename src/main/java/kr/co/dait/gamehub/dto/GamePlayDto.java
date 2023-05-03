package kr.co.dait.gamehub.dto;

import java.math.BigInteger;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GamePlayDto {

    // @NotNull(message = " 필수 입력 값입니다.") // null x, ""," " 허용
    // @NotEmpty(message = "필수 입력 값입니다.") // " " 허용
    // @NotBlank(message = "필수 입력 값입니다.") // 전부 허용 안함
    // @Email(message = "이메일 형식으로 입력해주세요.")
    // @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    // @Min(value = 1, message = "최소 주문 수량은 1개 입니다.")
    // @Max(value = 999, message = "최대 주문 수량은 999개 입니다.")
    // @Size(min = 1, max = 10, message = "이름은 1 ~ 10자 이여야 합니다!") 하이버네이트 종속x

    @NotNull(message = "gameId는 필수 파라미터 입니다.")
    private Long gameId;
    @NotBlank(message = "gameApikey는 필수 파라미터 입니다.")
    private String gameApikey;

    private Long playId;

    private String playerName;
    private String playerComment;
    private BigInteger playScore;
    private Long playTime;
    private String playUuid;
    private String etcText;

    
    private Long playMinusDays = 7L;
}
