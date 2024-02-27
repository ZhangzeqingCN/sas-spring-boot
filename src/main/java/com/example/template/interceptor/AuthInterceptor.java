package com.example.template.interceptor;

import com.example.template.utils.MyJwtUtil;

import org.springframework.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class AuthInterceptor implements HandlerInterceptor {

    public final MyJwtUtil jwtUtil;

    @Override
    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                           @NotNull Object handler, @Nullable ModelAndView modelAndView) {
    }

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                             @NotNull Object handler) {
        String token = request.getHeader("token");
        if (token != null) {
            String username = jwtUtil.decodeToken(token);
            log.info(username);
            request.setAttribute("username", username);
        } else {
            log.info("no token");
        }
        return true;
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                @NotNull Object handler, @Nullable Exception ex) {
    }

}
