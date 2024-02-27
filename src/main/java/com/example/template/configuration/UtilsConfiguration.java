package com.example.template.configuration;

import com.example.template.utils.MyJwtUtil;
import com.example.template.utils.MyState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsConfiguration {
    @Bean
    public MyJwtUtil jwtUtil() {
        return new MyJwtUtil();
    }

    @Bean
    public MyState state() {
        return new MyState();
    }
}
