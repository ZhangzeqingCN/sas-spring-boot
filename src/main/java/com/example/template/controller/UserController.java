package com.example.template.controller;

import com.example.template.dto.TResult;
import com.example.template.dao.User;
import com.example.template.repository.UserRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/user")
@Tag(name = "UserController")
@RequiredArgsConstructor
public class UserController {

    public final UserRepository userRepository;

    @GetMapping("/current")
    public TResult<User> getUser(@RequestAttribute("username") @Schema(defaultValue = "ZZQ") String username) {
        Optional<User> optionalUser = userRepository.findByName(username);
        if (optionalUser.isEmpty()) {
            return TResult.error("User " + username + "not found");
        } else {
            return TResult.success(optionalUser.get());
        }
    }
}
