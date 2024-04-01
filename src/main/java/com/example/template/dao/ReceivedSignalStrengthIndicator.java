package com.example.template.dao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Timestamp;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class ReceivedSignalStrengthIndicator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(defaultValue = "1")
    @NotBlank
    Integer id;

    Float strength;

    @OneToOne
    AP ap;

    Timestamp receivedTime;

    @OneToOne
    Device device;
}
