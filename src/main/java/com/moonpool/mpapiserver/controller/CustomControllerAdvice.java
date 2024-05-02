package com.moonpool.mpapiserver.controller;

import com.moonpool.mpapiserver.config.JwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(JwtException.class)
    protected ResponseEntity<?> handleJwtException(JwtException e){
        String message = e.getMessage();
        return ResponseEntity.ok().body(Map.of("Error",message));
    }
}
