package com.example.template.dto.user;

import com.example.template.dao.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import static com.example.template.dao.User.testToken;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignInResponse {
    @Schema(defaultValue = testToken)
    String token;
    @Schema(defaultValue = testToken)
    String refreshToken;
    User user;
}
