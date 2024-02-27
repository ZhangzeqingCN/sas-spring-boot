package com.example.template.dto.test;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DateDomain {
    @Schema(defaultValue = testDate, example = testDate)
    Date date;
    public static final String testDate = "2023-06-23T16:13:33.467+00:00";
}
