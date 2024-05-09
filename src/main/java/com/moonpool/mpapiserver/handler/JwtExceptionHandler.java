package com.moonpool.mpapiserver.handler;

public class JwtExceptionHandler extends RuntimeException{
    public JwtExceptionHandler(String message){
        super(message);
    }
}
