package kr.co.dait.gamehub.constant;

import lombok.Getter;

@Getter
public enum ResponseCode {
    //custom
    SUCCESS(200,"success","정상")
    ,LOGIN_SUCCESS(200,"success","로그인에 성공하였습니다")
    ,LOGOUT_SUCCESS(200,"success","로그아웃에 성공하였습니다")
    ,ERROR(400,"error","에러가 발생하였습니다")
    ,LOGIN_ERROR(400,"error","아이디 또는 비밀번호를 확인해주세요")
    ,UNAUTHORIZED_ERROR(401,"error","인증되지 않은 사용자 입니다")
    ,ACCESS_DENIED_ERROR(403,"error","권한이 없습니다")

    //basic
    ,OK(200,"success","OK")
    ,CREATED(201,"success","CREATED")
    ,NO_CONTENT(204,"success","NO_CONTENT")
    ,BAD_REQUEST(400,"error","BAD_REQUEST")
    ,UNAUTHORIZED(401,"error","UNAUTHORIZED")
    ,FORBIDDEN(403,"error","FORBIDDEN")
    ,NOT_FOUND(404,"error","NOT_FOUND")
    ,INTERNAL_SERVER_ERROR(500,"error","INTERNAL_SERVER_ERROR")
    ,SERVICE_UNAVAILABLE(503,"error","SERVICE_UNAVAILABLE")
    ,DB_ERROR(600,"error","DB_ERROR")
    ;

    private int code;
    private String status;
    private String message;

    private ResponseCode(int code, String status, String message){
        this.code = code;
        this.status = status;
        this.message = message;
    }
}

