package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.dtos.CommonErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

//controller단에서 발생하는 모든 예외를 모니터링하여 발생하는 예외를 intercepting함
@ControllerAdvice
public class CommonExceptionHandler {
//    EntityNotFoundException예외가 발생할 경우
    @ExceptionHandler(EntityNotFoundException.class)
//    매개변수로 예외가 주입
    public ResponseEntity<?> entityNotFound(EntityNotFoundException e){
        return new ResponseEntity<>(new CommonErrorDto(HttpStatus.NOT_FOUND.value(), e.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegal(IllegalArgumentException e){
        return new ResponseEntity<>(new CommonErrorDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> Method(MethodArgumentNotValidException e){
        return new ResponseEntity<>(new CommonErrorDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()),HttpStatus.BAD_REQUEST);
    }
}
