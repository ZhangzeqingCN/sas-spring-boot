package com.example.template.dao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
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
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(defaultValue = "1")
    @NotBlank
    Integer id;

    @OneToOne
    Device relativeDevice;

    @OneToOne
    User relativeUser;

    Integer code;

    String content;

    Timestamp relativeTime;

    Timestamp limitTime;

    Timestamp startTime;

    Timestamp durationTime;
}
