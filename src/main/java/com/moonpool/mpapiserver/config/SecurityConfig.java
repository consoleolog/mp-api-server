package com.moonpool.mpapiserver.config;

import com.moonpool.mpapiserver.handler.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;
@Log4j2
@RequiredArgsConstructor
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.cors(cors -> { //cors설정을 사용하겠다 라는 코드
            cors.configurationSource(corsConfigurationSource());
        });
//        http.sessionManagement(session -> {//세션 생성 안함
//            session.sessionCreationPolicy(SessionCreationPolicy.NEVER);
//        });
        http.csrf((csrf)->csrf.disable()); //csrf 비활성화

        http.authorizeHttpRequests(config->{
            config.requestMatchers("/**").permitAll();
        });
        http.formLogin(formLogin -> {
            formLogin.loginPage("/mp/members/login");
//            formLogin.successHandler(new LoginSuccessHandler());
//            formLogin.failureHandler(new LoginFailHandler());
//            formLogin.failureHandler(new LoginFailHandler());
            formLogin.defaultSuccessUrl("/mp/members");
        });
        // UsernamePasswordAuthenticationFilter.class 전에 jwtcheckfilter 실행 시켜줘
//        http.addFilterBefore(new JwtCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        // 멤버 권한 예외 전역 처리
//        http.exceptionHandling(config -> {
//            config.accessDeniedHandler(new CustomAccessDeniedHandler());
//        });
        http.logout(logout ->
                logout.logoutUrl("/mp/members/logout")
                        .invalidateHttpSession(true) // HTTP 세션 무효화
                        .deleteCookies("JSESSIONID") // 쿠키 삭제
                        .logoutSuccessUrl("/mp/members")
        );

        return http.build();
    }
//    @Bean //cors 설정
//    public CorsConfigurationSource corsConfigurationSource(){
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","HEAD","OPTIONS"));
//        configuration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**",configuration);
//        return source;
//    }
    @Bean //cors 설정
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        //http://localhost:3000","https://consoleolog.github.io/
        configuration.setAllowedOrigins(List.of("http://localhost:3000","https://consoleolog.github.io/"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","HEAD","OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }
}
