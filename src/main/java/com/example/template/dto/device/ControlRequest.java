package com.example.template.dto.device;

import com.example.template.dao.Message;
import lombok.*;

/**
 * 远程控制信息查询请求
 * 定时请求
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ControlRequest {
    Integer deviceId;
    Message message;
}
