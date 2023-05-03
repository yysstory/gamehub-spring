package kr.co.dait.gamehub.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BaseTimeDto {
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
