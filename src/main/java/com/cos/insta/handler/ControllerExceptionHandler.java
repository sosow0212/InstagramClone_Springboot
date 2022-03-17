package com.cos.insta.handler;

import com.cos.insta.handler.ex.CustomApiException;
import com.cos.insta.handler.ex.CustomException;
import com.cos.insta.handler.ex.CustomValidationApiException;
import com.cos.insta.handler.ex.CustomValidationException;
import com.cos.insta.util.Script;
import com.cos.insta.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice // 이 어노테이션으로 인해 다른 곳에서 Exception이 발생하면 모두 이곳으로 낚아챈다.
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class) // 런타임Exception이 발생하는 모든 Exception을 이 함수가 가로챈다
    public String validationException(CustomValidationException e) {
        // CMRespDto, Script 비교
        // 1. 클라이언트에게 응답할 때에는 Script가 좋다.
        // 2. Ajax통신 - CMRespDto
        // 3. Android 통신 - CMRespDto
        if (e.getErrorMap() == null) {
            return Script.back(e.getMessage());
        } else {
            return Script.back(e.getErrorMap().toString());
        }
    }


    @ExceptionHandler(CustomException.class) // 런타임Exception이 발생하는 모든 Exception을 이 함수가 가로챈다
    public String exception(CustomException e) {
        return Script.back(e.getMessage());
    }


    @ExceptionHandler(CustomValidationApiException.class) // 런타임Exception이 발생하는 모든 Exception을 이 함수가 가로챈다
    public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
        return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomApiException.class) // 런타임Exception이 발생하는 모든 Exception을 이 함수가 가로챈다
    public ResponseEntity<?> apiException(CustomValidationApiException e) {
        return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
}
