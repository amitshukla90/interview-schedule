package com.example.interviewscheduler.controller;

import com.example.interviewscheduler.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity handleUserException(UserException userException) {
        log.error("Exception: ", userException);
        return new ResponseEntity(userException.getError(), HttpStatus.BAD_REQUEST);
    }
}
