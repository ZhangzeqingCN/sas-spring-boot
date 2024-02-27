package com.example.template.migrate;

import cn.hutool.core.io.file.FileWriter;
import com.example.template.dao.*;
import com.example.template.dao.embed.IPAddress;
import com.example.template.dao.embed.MACAddress;
import com.example.template.dao.embed.Position;
import com.example.template.dao.enums.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
@Slf4j
public class MigrateTests {

    @NotNull
    String upperFirstLetter(@NotNull String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    String migrateClass(@NotNull Class<?> cls) {
        val s = new StringBuilder();
        s.append(String.format("type %s struct {\n", cls.getSimpleName()));
        for (Field field : cls.getDeclaredFields()) {
            val fieldType = field.getType();
            var modifiers = fieldType.getModifiers();
            val fieldName = upperFirstLetter(field.getName());
            boolean isEmbedded = false;
            boolean isPrimaryKey = false;
            if ((field.getModifiers() & java.lang.reflect.Modifier.STATIC) != 0) {
                // static
                continue;
            }
            var fieldTypeName = fieldType.getSimpleName();
            if (fieldName.equals("Id")) {
                isPrimaryKey = true;
            }
            fieldTypeName = switch (fieldTypeName) {
                case "Integer" -> "int32";
                case "Timestamp" -> "time.Time";
                case "Float" -> "float32";
                case "Boolean" -> "bool";
                case "Byte" -> "byte";
                case "String" -> "string";
                default -> {
                    if (!fieldType.isEnum()) {
                        isEmbedded = true;
                    }
                    yield fieldTypeName;
                }
            };
            if (isEmbedded) {
                s.append(String.format("\t%s %s `gorm:\"embedded\"`\n", fieldName, fieldTypeName));
            } else if (isPrimaryKey) {
                s.append(String.format("\t%s %s `gorm:\"primaryKey\"`\n", fieldName, fieldTypeName));
            } else {
                s.append(String.format("\t%s %s\n", fieldName, fieldTypeName));
            }
        }
        s.append("}\n\n");
        return s.toString();
    }

    String migrate(@NotNull Class<?> cls) {
        if (cls.isEnum()) {
            return migrateEnum(cls);
        } else {
            return migrateClass(cls);
        }
    }

    String migrateEnum(@NotNull Class<?> cls) {
        val s = new StringBuilder();
        val clsName = cls.getSimpleName();
        s.append(String.format("type %s uint32\n\n", clsName));
        s.append("const (\n");
        val fields = cls.getFields();
        s.append(String.format("\t%s_%s %s= iota\n", clsName, fields[0].getName(), clsName));
        for (int i = 1; i < fields.length; i++) {
            val field = fields[i];
            s.append(String.format("\t%s_%s\n", clsName, field.getName()));
        }
        s.append(")\n\n");
        return s.toString();
    }

    String migrateAll() {
        val stringBuilder = new StringBuilder("package main\nimport \"time\"\n");
        List<Class<?>> clsList = List.of(
                Device.class,
                DeviceState.class,
                User.class,
                Position.class,
                Gateway.class,
                IPAddress.class,
                UserType.class,
                Gender.class,
                DeviceType.class,
                RepeatTimeType.class,
                AP.class,
                Field.class,
                MACAddress.class,
                Farm.class,
                ReceivedSignalStrengthIndicator.class
        );
        for (Class<?> aClass : clsList) {
            stringBuilder.append(migrate(aClass));
        }
        return stringBuilder.toString();
    }

    @Test
    void testMigrateAll() {
        System.out.println(migrateAll());
    }

    @Test
    void testIO() {
        val path = "E:\\Users\\Administrator\\Documents\\GitHub\\sas-go-udp-device-service\\dao.go";
        val w = new FileWriter(path);
        try {
            w.write(migrateAll());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
