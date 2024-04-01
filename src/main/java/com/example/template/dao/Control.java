package com.example.template.dao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Timestamp;

/**
 * @apiNote 用于记录用户针对设备的操作
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Control {
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
