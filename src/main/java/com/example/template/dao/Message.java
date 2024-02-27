package com.example.template.dao;

import com.example.template.dao.enums.EnvDataType;
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
@Table(name = "t_Message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(defaultValue = "1")
    @NotBlank
    Integer id;

    @ManyToOne
    Device device;

    Timestamp sendTime;

    Timestamp receivedTime;

    String content;

    EnvDataType envDataType;

    Float envDataValue;

    Float battery;
}
