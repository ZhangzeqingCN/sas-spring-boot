package com.example.template.dao.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.jetbrains.annotations.NotNull;


public enum Gender {
    Unknown,
    Male,
    Female;

    @SuppressWarnings("unused")
    @JsonValue
    String toJsonValue() {
        return toString();
    }

    @SuppressWarnings("unused")
    @Converter
    public static class MyConverter implements AttributeConverter<Gender, String> {
        @Override
        public String convertToDatabaseColumn(@NotNull Gender g) {
            return g.toString();
        }

        @Override
        public Gender convertToEntityAttribute(@NotNull String g) {
            return Gender.valueOf(g);
        }
    }
}
