package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice // 이 어노테이션으로 인해 다른 곳에서 Exception이 발생하면 모두 이곳으로 낚아챈다.
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class) // 런타임Exception이 발생하는 모든 Exception을 이 함수가 가로챈다
    public Map<String, String> validationException(CustomValidationException e) {
        return e.getErrorMap();
    }
}
