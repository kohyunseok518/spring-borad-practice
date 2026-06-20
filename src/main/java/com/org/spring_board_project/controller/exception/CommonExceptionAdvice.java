package com.org.spring_board_project.controller.exception;

import com.sun.nio.sctp.NotificationHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

// 이 클래스는 모든 컨트롤러에서 발생하는 에러를 전담해서 처리할게라는 어노테이션
@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {

    // JSP(화면)을 찾지 말고, 글자 자체를 브라우저에게 던져라, 이 어노테이션이 없으면 해당 이름의 jsp 파일을 찾아버림
    @ResponseBody
    @ExceptionHandler(NumberFormatException.class)
    public String exceptNumber(NumberFormatException numberFormatException) {
        log.error("-----------------------------------");
        log.error(numberFormatException.getMessage());
        return "NUMBER FORMAT EXCEPTION";
    }

    // 범용적인 예외처리
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exceptCommon(Exception exception) {
        log.error("-----------------------------------");
        log.error(exception.getMessage());

        StringBuffer buffer = new StringBuffer("<ul>");

        buffer.append("<li>" + exception.getMessage() + "</li>");

        Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
            buffer.append("<li>" + stackTraceElement + "</li>");
        });
        buffer.append("</ul>");

        return buffer.toString();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {

        return "custom404";
    }
}
