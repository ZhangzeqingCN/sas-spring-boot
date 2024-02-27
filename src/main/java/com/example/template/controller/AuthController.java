package com.example.template.controller;


import com.example.template.dto.Result;
import com.example.template.dto.TResult;
import com.example.template.dto.auth.SignInResponse;
import com.example.template.dto.auth.SignInRequest;
import com.example.template.dto.auth.SignUpRequest;
import com.example.template.dao.User;
import com.example.template.repository.UserRepository;
import com.example.template.utils.MyJwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/auth")
@Slf4j
@Tag(name = "AuthController", description = "AuthController")
@RequiredArgsConstructor
public class AuthController {

    public final UserRepository userRepository;

    public final MyJwtUtil jwtUtil;

    @PostMapping("/signIn")
    @NotNull
    public TResult<SignInResponse> signIn(@RequestBody @NotNull @Validated SignInRequest signInRequest) {
        Optional<User> optionalUser = userRepository.findByNameAndPassword(signInRequest.getUsername(), signInRequest.getPassword());
        if (optionalUser.isEmpty()) {
            String message = String.format("wrong username %s or password %s", signInRequest.getUsername(), signInRequest.getPassword());
            log.info(message);
            return TResult.<SignInResponse>error(message).addErrors(message).addErrors(signInRequest);
        }

        log.info(String.format("login username %s with password %s", signInRequest.getUsername(), signInRequest.getPassword()));
        String token = jwtUtil.createToken(signInRequest.getUsername());
        return TResult.success(SignInResponse.builder().token(token).user(optionalUser.get()).build());
    }

    @PostMapping("/signUp")
    @NotNull
    public Result signUp(@RequestBody @NotNull @Validated SignUpRequest signUpRequest) {

        if (!Objects.equals(signUpRequest.getPassword1(), signUpRequest.getPassword2())) {
            String message = "inconsistent two passwords";
            log.info(message);
            return Result.error(message);
        }

        if (userRepository.existsByName(signUpRequest.getUsername())) {
            String message = String.format("username %s already exists", signUpRequest.getUsername());
            log.info(message);
            return Result.error(message).addErrors(signUpRequest.getUsername());
        }

        User user = new User();
        user.setName(signUpRequest.getUsername());
        user.setPassword(signUpRequest.getPassword1());

        userRepository.save(user);

        return Result.success();
    }

    @PostMapping("/signOut")
    public TResult<Object> signOut(@RequestBody @NotNull Object object) {
        return TResult.success("?");
    }

    @PostMapping("/signOff")
    public TResult<Object> signOff(@RequestBody @NotNull Object object) {
        return TResult.success("?");
    }

    @GetMapping("/getAuthorization")
    public TResult<String> getAuthorization() {
        return TResult.success();
    }
}
