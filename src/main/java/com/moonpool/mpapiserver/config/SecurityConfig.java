package com.moonpool.mpapiserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
//        http.cors(cors -> { //cors설정을 사용하겠다 라는 코드
//            cors.configurationSource(corsConfigurationSource());
//        });
        http.csrf((csrf)->csrf.disable()); //csrf 비활성화

        http.authorizeHttpRequests(config->{
            config.requestMatchers("/**").permitAll();
        });
        http.formLogin((formLogin) -> {
            formLogin.loginPage("/mp/members/login")
                    .defaultSuccessUrl("/");
        });
        http.logout(logout ->
                logout.logoutUrl("/mp/members/logout")
                        .logoutSuccessUrl("http://localhost:3000/")
                        .invalidateHttpSession(true) // HTTP 세션 무효화
                        .deleteCookies("JSESSIONID") // 쿠키 삭제
        );
        return http.build();
    }
}
