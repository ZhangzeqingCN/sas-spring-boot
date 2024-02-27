package com.example.template.dao;

import com.example.template.dao.embed.Position;
import com.example.template.dao.enums.DeviceState;
import com.example.template.dao.enums.DeviceType;
import com.example.template.dao.enums.RepeatTimeType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Timestamp;

@SuppressWarnings("JpaDataSourceORMInspection")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(defaultValue = "1")
    @NotBlank
    Integer id;
    @Schema(defaultValue = "Unknown")
    @Builder.Default
    DeviceType deviceType = DeviceType.Unknown;

    // 缓冲大小，即等待多少条信息才一起发出
    Integer cacheSize;

    // 平均值的个数
    Integer meanSize;

    // 发送消息的间隔
    Timestamp messageSendInterval;

    @ManyToOne
    Gateway gateway;

    @Schema(defaultValue = "Unknown")
    @Builder.Default
    DeviceState deviceState = DeviceState.Unknown;

    // 最近访问服务器的时间
    Timestamp lastAccessTime;

    // 最近自检时间
    Timestamp lastCheckTime;

    // 运行时间
    Timestamp runningTime;

    // 累计消息数
    Integer messageCount;

    // 自动开关机时间间隔类型
    RepeatTimeType autoPowerTimeType;

    // 自动开启时间
    Timestamp autoPowerOnOffsetTime;

    // 自动关闭时间
    Timestamp autoPowerOffOffsetTime;

    // 位置更新间隔
    Timestamp positionUpdateInterval;

    Timestamp lastPositionUpdateTime;

    @Embedded
    Position position;

    Integer maxBattery;

    Integer currentBattery;
}
