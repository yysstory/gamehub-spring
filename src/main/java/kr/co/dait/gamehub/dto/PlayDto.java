package kr.co.dait.gamehub.dto;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayDto extends BaseTimeDto{

    // @NotBlank(message = "이름은 필수 입력 값입니다.") nullx ""x " "x
    // @NotEmpty(message = "이메일은 필수 입력 값입니다.") nullx ""x 
    // @NotNull(message = "필수 입력 값입니다.") // nullx

    // @Email(message = "이메일 형식으로 입력해주세요.")
    // @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    // @Min(value = 1, message = "최소 값은 1 입니다.")
    // @Max(value = 999, message = "최대 값은 999 입니다.")
       
    private Long playId;
    private String playerName;
    private String playerComment;
    private BigInteger playScore;
    private Long playTime;
    private String playUuid;
    private String etcText;

}
