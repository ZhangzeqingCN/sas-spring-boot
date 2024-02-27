package com.example.template.dao.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.jetbrains.annotations.NotNull;

public enum TaskState {
    Ready,
    Suspended,
    Going,
    Finished,
    Waiting;

    @SuppressWarnings("unused")
    @Converter
    public static class MyConverter implements AttributeConverter<TaskState, String> {
        @Override
        public String convertToDatabaseColumn(@NotNull TaskState g) {
            return g.toString();
        }

        @Override
        public TaskState convertToEntityAttribute(@NotNull String g) {
            return TaskState.valueOf(g);
        }
    }
}
