package com.spring.sns.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AppBaseException extends RuntimeException{

    private ErrorCode errorCode;
    private String message;


//    메세지가 필용없는 경우 필요한 생성자
    public AppBaseException (ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = null;
    }

    @Override
    public String getMessage() {
        if (message == null) {
            return errorCode.getMessage();
        }
        return String.format("%s, %s", errorCode.getMessage(), message);
    }
}
