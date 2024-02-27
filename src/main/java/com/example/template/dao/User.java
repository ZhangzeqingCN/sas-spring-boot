package com.example.template.dao;

import com.example.template.dao.enums.Gender;
import com.example.template.dao.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Timestamp;


@SuppressWarnings("JpaDataSourceORMInspection")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "t_User")
public class User {

    public static final String testUsername = "User Name";
    @Id
    @Schema(defaultValue = testUsername)
    @NotBlank
    String name;

    public static final String testPassword = "123456789";
    @Schema(defaultValue = testPassword, example = testPassword)
    @JsonIgnore
    String password;

    public static final String testCode = "932672";
    @Schema(defaultValue = testCode, example = testCode)
    @JsonIgnore
    String code;

    @Schema(defaultValue = "Unknown")
    @Builder.Default
    Gender gender = Gender.Unknown;

    public static final String testEmail = "123@example.com";
    @Email
    @Schema(defaultValue = testEmail, minLength = 5, maxLength = 30)
    String email;

    public static final String testPhone = "12312341234";
    @Schema(defaultValue = testPhone, minLength = 11, maxLength = 11)
    String phone;

    public static final String testAddress = "北京市";
    @Schema(defaultValue = testAddress, minLength = 10, maxLength = 128)
    String address;

//    public static final String testToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhdXRoZW50aWNhdGlvbiIsInQtdXNlcm5hbWUiOiJaWlEiLCJpc3MiOiJpc3N1ZXIiLCJleHAiOjM4MzQwNjU1NDYsImlhdCI6MTY4NjU4MTg5OX0.YlalGUUUR1bnz4u1ZRA_l8NCqOgXCV17P3sTuGCYoN8";
    public static final String testToken = "eyJ...YoN8";
    String token;

    String refreshToken;

    Timestamp signUpTime;

    Timestamp lastSignInTime;

    Timestamp resetPasswordTime;

    UserType userType;

    Integer lastOperationTime;
}
