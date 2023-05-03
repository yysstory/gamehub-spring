package kr.co.dait.gamehub.config;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.co.dait.gamehub.constant.ResponseCode;
import kr.co.dait.gamehub.util.ResponseData;

@RestControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseData exceptionHandle(Exception exception) {
        ResponseData responseData = new ResponseData(ResponseCode.INTERNAL_SERVER_ERROR);
        responseData.setMessage(exception.getMessage());
        return responseData;
    }

    
    @ExceptionHandler({BindException.class})
    public ResponseData bindException(BindException exception) {

        BindingResult bindingResult = exception.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
          //stringBuilder.append(fieldError.getField()).append(":");
          stringBuilder.append(fieldError.getDefaultMessage());
          stringBuilder.append("\n");
        }
        ResponseData responseData = new ResponseData(ResponseCode.INTERNAL_SERVER_ERROR);
        responseData.setMessage(stringBuilder.toString());
        return responseData;    
    }
    
}
