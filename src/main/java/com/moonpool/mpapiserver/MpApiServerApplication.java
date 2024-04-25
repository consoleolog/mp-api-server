package com.moonpool.mpapiserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MpApiServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpApiServerApplication.class, args);
    }

}
