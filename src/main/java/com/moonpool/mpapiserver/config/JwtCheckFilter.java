package com.moonpool.mpapiserver.config;

import com.google.gson.Gson;
import com.moonpool.mpapiserver.dto.UserDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class JwtCheckFilter extends OncePerRequestFilter {
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        // jwt 체크 X
        if (path.startsWith("/mp/members/")){
            return true;
        }
        // jwt 체크 O
        return false;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 로그인 경로로 post 요청하면 실행할코드
        String authHeaderStr = request.getHeader("Authorization");
        try{
            // Bearer 타입(이걸로 쓰래)  7개의 jwt 문자열
            String accessToken = authHeaderStr.substring(7);
            Map<String, Object> claims = JwtConfig.validateToken(accessToken);
            Long id = (Long) claims.get("id");
            String username = (String) claims.get("username");
            String password = (String) claims.get("password");
            String displayName = (String) claims.get("displayName");
            String intro = (String) claims.get("intro");
            String educationState = (String) claims.get("educationState");
            Integer coin = (Integer) claims.get("coin");
            List<String> roleNames = (List<String>) claims.get("roleNames");
            UserDto userDto = new UserDto(id, username,password,displayName,intro,educationState,coin,roleNames);

            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(userDto, password, userDto.getAuthorities());

            // 인증 완료하면 contextholder 에 userdto를 저장
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            filterChain.doFilter(request,response);
        }catch (Exception e){
            Gson gson = new Gson();
            String message = gson.toJson(Map.of("Error","Error_Access_Token"));
            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(message);
            printWriter.close();
        }
    }
}
