package com.example.template.dto.device;

import com.example.template.dao.Message;
import com.example.template.dao.enums.EnvDataType;
import lombok.*;

/**
 * 发送农田环境数据的请求
 * 定时请求
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EnvDataRequest {
    Message message;
}
