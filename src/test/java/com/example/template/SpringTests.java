package com.example.template;

import com.example.template.utils.MyState;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SuppressWarnings("EmptyMethod")
@SpringBootTest
@Slf4j
public class SpringTests {

    @Autowired
    MyState state;


    @SuppressWarnings("EmptyMethod")
    @Test
    public void testDateFormat() {
        log.info(state.message());
    }
}
