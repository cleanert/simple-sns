package com.spring.sns.web.exception;

import com.spring.sns.web.exception.response.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(AppBaseException.class)
    public ResponseEntity<?> applicationHandler(AppBaseException e) {
        log.error("Error occurs {}", e.toString());

        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(ResultResponse.error(e.getErrorCode().name()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> applicationHandler(RuntimeException e) {
        log.error("Error occurs {}", e.toString());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResultResponse.error(ErrorCode.INTERNAL_SERVER_ERROR.name()));
    }
}
