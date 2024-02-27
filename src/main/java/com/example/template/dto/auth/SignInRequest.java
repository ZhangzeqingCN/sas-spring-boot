package com.example.template.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import static com.example.template.dao.User.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignInRequest {
    @NotBlank
    @Schema(description = "username", defaultValue = testUsername, requiredMode = Schema.RequiredMode.REQUIRED)
    String username;
    @NotBlank
    @Schema(description = "password", defaultValue = testPassword, requiredMode = Schema.RequiredMode.REQUIRED)
    String password;
}
