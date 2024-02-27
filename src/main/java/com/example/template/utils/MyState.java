package com.example.template.utils;

import org.springframework.stereotype.Component;

@Component
public class MyState {
    public Float fireProb;

    public String message() {
        return "!" + fireProb;
    }
}
