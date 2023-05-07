package kr.co.dait.gamehub.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
        
    private Long userId;
    private String userName;

    @Length(min=8, max=100, message = "비밀번호는 8자 이상, 100자 이하로 입력해주세요")
    @NotBlank(message = "비밀번호는 필수 입력값 입니다.")
    private String userPassword;
    
    @Email(message = "이메일 형식으로 입력해주세요.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String userEmail;
    
    private String userAddress;

}
