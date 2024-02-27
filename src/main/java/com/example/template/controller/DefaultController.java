package com.example.template.controller;

import com.example.template.dto.TResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping
@Tag(name = "DefaultController")
public class DefaultController {
    @RequestMapping
    public TResult<Object> getDefaultMessage() {
        return TResult.error("DefaultMessage???");
    }


}
