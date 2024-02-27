package com.example.template.dao.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.jetbrains.annotations.NotNull;

public enum DeviceState {
    Unknown,
    Running,
    Wrong,
    NoAccess,
    Stopped,
    Died;

    @SuppressWarnings("unused")
    @Converter
    public static class MyConverter implements AttributeConverter<DeviceState, String> {
        @Override
        public String convertToDatabaseColumn(@NotNull DeviceState g) {
            return g.toString();
        }

        @Override
        public DeviceState convertToEntityAttribute(@NotNull String g) {
            return DeviceState.valueOf(g);
        }
    }
}
