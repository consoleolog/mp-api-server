package com.moonpool.mpapiserver.config;

public class JwtException extends RuntimeException{
    public JwtException(String message){
        super(message);
    }
}
