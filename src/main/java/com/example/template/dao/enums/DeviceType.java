package com.example.template.dao.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.jetbrains.annotations.NotNull;

public enum DeviceType {
    Unknown,
    TH,
    L,
    CO2,
    PH;

    @SuppressWarnings("unused")
    @Converter
    public static class MyConverter implements AttributeConverter<DeviceType, String> {
        @Override
        public String convertToDatabaseColumn(@NotNull DeviceType g) {
            return g.toString();
        }

        @Override
        public DeviceType convertToEntityAttribute(@NotNull String g) {
            return DeviceType.valueOf(g);
        }
    }
}
