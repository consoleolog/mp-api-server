package com.moonpool.mpapiserver.config;

import com.moonpool.mpapiserver.handler.LoginFailHandler;
import com.moonpool.mpapiserver.handler.LoginSuccessHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Log4j2
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.cors(cors -> { //cors설정을 사용하겠다 라는 코드
            cors.configurationSource(corsConfigurationSource());
        });
        http.csrf((csrf)->csrf.disable()); //csrf 비활성화

        http.authorizeHttpRequests(config->{
            config.requestMatchers("/**").permitAll();
        });
        log.info(".........지금부터 폼 로그인 시작");
        http.formLogin((formLogin) -> {
            formLogin.loginPage("/mp/members/login")
//                    .successHandler(new LoginSuccessHandler())
//                    .failureHandler(new LoginFailHandler())
                    .loginProcessingUrl("/mp/members/login")
                    .defaultSuccessUrl("/mp/members");
        });
        log.info("......... 폼 로그인 끝");
        http.logout(logout ->
                logout.logoutUrl("/mp/members/logout")
                        .logoutSuccessUrl("/mp/members")
                        .invalidateHttpSession(true) // HTTP 세션 무효화
                        .deleteCookies("JSESSIONID") // 쿠키 삭제
        );
        http.sessionManagement(session ->
                session.maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
        );
        return http.build();
    }
    @Bean //cors 설정
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000","https://consoleolog.github.io/"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","HEAD","OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }
}
