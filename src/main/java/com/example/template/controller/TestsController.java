package com.example.template.controller;

import com.example.template.dto.TResult;
import com.example.template.dto.test.DateDomain;
import com.example.template.dto.udp.UdpPost;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

import static com.example.template.dao.User.testToken;
import static com.example.template.dao.User.testUsername;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/test")
@Tag(name = "TestsController")
@Slf4j
public class TestsController {

    @Value("${server.port}")
    Integer port;

    @GetMapping
    public TResult<String> getTestMessage() {
        return TResult.success("Hello");
    }

    @GetMapping("/getGreetMessageWithHeadersToken")
    public TResult<String> getGreetMessageWithHeadersToken(@RequestHeader("token") @Schema(defaultValue = testToken) String username) {
        return TResult.success("Your token " + username);
    }

    @GetMapping("/getGreetMessageWithUsernameAttribute")
    public TResult<String> getGreetMessageWithUsernameAttribute(@RequestAttribute("username") @Schema(defaultValue = testUsername) String username) {
        return TResult.success("Hello " + username);
    }

    @GetMapping("/port")
    @Operation
    public TResult<Integer> getPort() {
        return TResult.success(port);
    }

    @GetMapping("/date")
    public TResult<Date> getDate() {
        return TResult.success(new Date());
    }

    @PostMapping("/date")
    public TResult<Date> postDate(@NotNull @RequestBody DateDomain dateDomain) {
        return TResult.success(dateDomain.getDate());
    }

    @PostMapping("/testPostFile")
    @Operation(description = "testPostFile")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
            mediaType = "multipart/form-data",
            schema = @Schema(type = "object"),
            schemaProperties = @SchemaProperty(
                    name = "file",
                    schema = @Schema(type = "string", format = "binary")
            )
    ))
    @ApiResponse(content = @Content(mediaType = "multipart/form-data"))
    public TResult<Void> testPostFile(@RequestPart MultipartFile file, HttpServletResponse response) {
        return TResult.success();
    }

    @PostMapping("/testPostUdp")
    public TResult<Void> testPostUdp(@RequestBody UdpPost udpPost) {
        return TResult.success();
    }
}
