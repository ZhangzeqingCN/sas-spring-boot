package com.example.template.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import static com.example.template.dao.User.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignUpRequest {
    @NotBlank
    @Schema(defaultValue = testUsername)
    String username;
    @NotBlank
    @Schema(defaultValue = testPassword, description = "第一次密码")
    @Pattern(regexp = passwordRegex)
    String password1;
    @NotBlank
    @Schema(defaultValue = testPassword, description = "第二次密码")
    @Pattern(regexp = passwordRegex)
    String password2;
    public static final String passwordRegex = "^(?![a-zA-Z]+$)(?!\\d+$)(?![^\\da-zA-Z\\s]+$).{8,16}$";
}
