package com.example.template.dao.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.jetbrains.annotations.NotNull;

public enum UserType {
    None,
    Administrator,
    Operator,
    Observer,
    Supporter;

    @SuppressWarnings("unused")
    @Converter
    public static class MyConverter implements AttributeConverter<UserType, String> {
        @Override
        public String convertToDatabaseColumn(@NotNull UserType g) {
            return g.toString();
        }

        @Override
        public UserType convertToEntityAttribute(@NotNull String g) {
            return UserType.valueOf(g);
        }
    }
}
