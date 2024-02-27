package com.example.template.initializer;

import com.example.template.dao.User;
import com.example.template.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.example.template.dao.User.testPassword;
import static com.example.template.dao.User.testUsername;


/**
 * 数据库初始化
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class DataSourceInitializer implements CommandLineRunner {

    public final UserRepository userRepository;


    @SuppressWarnings("CanBeFinal")
    @Value("${dataInit}")
    boolean enabled = false;

    @Override
    public void run(String... args) {
        if (!enabled) return;
        log.info("DataSourceInitializer on");
        var user = User.builder().name(testUsername).password(testPassword).build();
        userRepository.save(user);
    }
}

