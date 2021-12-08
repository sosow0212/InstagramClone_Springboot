package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice // 이 어노테이션으로 인해 다른 곳에서 Exception이 발생하면 모두 이곳으로 낚아챈다.
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class) // 런타임Exception이 발생하는 모든 Exception을 이 함수가 가로챈다
    public String validationException(CustomValidationException e) {
        // CMRespDto, Script 비교
        // 1. 클라이언트에게 응답할 때에는 Script가 좋다.
        // 2. Ajax통신 - CMRespDto
        // 3. Android 통신 - CMRespDto
        return Script.back(e.getErrorMap().toString());
    }
}
