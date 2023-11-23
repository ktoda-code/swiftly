package com.ktoda.swiftly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
@EnableJpaAuditing
@EnableRetry
public class SwiftlyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwiftlyApplication.class, args);
    }

}
