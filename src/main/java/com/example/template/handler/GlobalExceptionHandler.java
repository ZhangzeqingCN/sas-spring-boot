package com.example.template.handler;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.template.dto.Result;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

/**
 * 全局异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleException(@NotNull Exception e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage()).addErrors(e.getClass()).addErrors(e);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Result handleMissingRequestHeaderException(@NotNull MissingRequestHeaderException e, @NotNull HttpServletResponse response) {
        return Result.error(e.getMessage()).addErrors(e.getClass()).addErrors(e);
    }

    @ExceptionHandler({ServletRequestBindingException.class, JWTDecodeException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Result handleMissingRequestHeaderException(@NotNull ServletRequestBindingException e, @NotNull HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return Result.error(e.getMessage()).addErrors(e.getClass()).addErrors(e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handleMethodArgumentNotValidException(@NotNull MethodArgumentNotValidException e) {
        String error = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
        return Result.error("密码邮箱等不符合要求").addErrors(error);
    }
}
