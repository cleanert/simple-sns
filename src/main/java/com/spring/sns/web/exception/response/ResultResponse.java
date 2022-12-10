package com.spring.sns.web.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResultResponse<T> {
//    어떤객체가 올지 모르니 제네릭으로 설계 (ex. login response, post response)
//    결과값에 대한 에러코드값
    private String resultCode;
//    결과값이 있으면 result
    private T result;


//    에러인경우
    public static ResultResponse<Void> error(String errorCode) {
        return new ResultResponse<>(errorCode, null);
    }

//    성공일경우
    public static <T> ResultResponse<T> success(T result) {
        return new ResultResponse<>("SUCCESS", result);
    }
}
