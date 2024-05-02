package com.moonpool.mpapiserver.handler;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Log4j2
@Service
public class LoginFailHandler implements AuthenticationFailureHandler  {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("................fail handler start./....");

        Gson gson = new Gson();

        String jsonStr = gson.toJson(Map.of("MESSAGE","ERROR_LOGIN"));
        response.setContentType("application/json"); //status 가 200 이지만 에러 메시지를 보내주는거임
        PrintWriter printWriter = response.getWriter();
        printWriter.println(jsonStr);
        printWriter.close();
    }
}
