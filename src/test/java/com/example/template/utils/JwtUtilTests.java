package com.example.template.utils;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SuppressWarnings("ALL")
@SpringBootTest
@Slf4j
public class JwtUtilTests {

    @Autowired
    private MyJwtUtil jwtUtil;


    @Test
    public void testGenerateToken() {
        var token = jwtUtil.createToken("ZZQ", Integer.MAX_VALUE);
        log.info(token);
    }
}