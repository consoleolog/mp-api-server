package com.moonpool.mpapiserver.config;

import com.google.gson.Gson;
import com.moonpool.mpapiserver.dto.UserDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
@Log4j2
public class JwtCheckFilter extends OncePerRequestFilter {
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws SecurityException {
        String path = request.getRequestURI();
        if (path.startsWith("/mp/members/")|| path.startsWith("/mp/problems") ||path.startsWith("/mp/carts/delete/all")){
            return true;
        } else if (path.startsWith("/mp/comments")){
            return true;
        }
        return false;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
        // 로그인 경로로 post 요청하면 실행할 코드
        String authHeader = request.getHeader("Authorization");
        log.info("----------------------------------dofilter");
        try {
            // Bearer 타입( 공식 문서에 이렇게 쓰라네)
            log.info(authHeader);
            String accessToken = authHeader.substring(7);
            Map<String, Object> claims = JwtConfig.validateToken(accessToken);
            var id = claims.get("id").toString();
            var username =  claims.get("username");
            var password =  claims.get("password");
            var displayName =  claims.get("displayName");
            var intro =  claims.get("intro");
            var educationState =  claims.get("educationState");
            var coin =  claims.get("coin");
            var roleNames =  claims.get("roleNames");
            Long a = Long.valueOf(id);
            UserDto userDto = new UserDto( a, (String) username, (String) password, (String) displayName, (String) intro, (String) educationState, (Integer) coin, (List<String>) roleNames);
            log.info(userDto);
            log.info(userDto.getAuthorities());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDto,password,userDto.getAuthorities());
            log.info(authenticationToken);
//             인증 완료하면 contextholder 에 userdto 를 저장
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
            log.info("----------------------checkfilter end");
        } catch (Exception e){
            Gson gson = new Gson();
            String message = gson.toJson(Map.of("Error","Error_Access_Token"));
            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(message);
            printWriter.close();
        }
    }
}
