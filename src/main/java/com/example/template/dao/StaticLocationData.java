package com.example.template.dao;

import com.example.template.dao.embed.Position;
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
public class StaticLocationData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(defaultValue = "1")
    @NotBlank
    Integer id;

    @Embedded
    Position position;

    Timestamp updateTime;
}
