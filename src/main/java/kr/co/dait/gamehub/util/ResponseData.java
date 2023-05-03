package kr.co.dait.gamehub.util;

import kr.co.dait.gamehub.constant.ResponseCode;
import lombok.Data;

@Data
public class ResponseData {
    
    private int code;
    private String status;
    private String message;
    private Object data;
    
    public ResponseData(ResponseCode responseCode){
        this.code = responseCode.getCode();
        this.status = responseCode.getStatus();
        this.message = responseCode.getMessage();
    }
    public ResponseData(ResponseCode responseCode, Object data){
        this.code = responseCode.getCode();
        this.status = responseCode.getStatus();
        this.message = responseCode.getMessage();
        this.data = data;
    }
}
