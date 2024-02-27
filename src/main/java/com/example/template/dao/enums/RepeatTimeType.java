package com.example.template.dao.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.jetbrains.annotations.NotNull;

public enum RepeatTimeType {
    Never,
    Yearly,
    Monthly,
    Weekly,
    Daily,
    Hourly,
    Minutely,
    Secondly;

    @SuppressWarnings("unused")
    @Converter
    public static class MyConverter implements AttributeConverter<RepeatTimeType, String> {
        @Override
        public String convertToDatabaseColumn(@NotNull RepeatTimeType g) {
            return g.toString();
        }

        @Override
        public RepeatTimeType convertToEntityAttribute(@NotNull String g) {
            return RepeatTimeType.valueOf(g);
        }
    }
}
