package com.example.template.dto.device;

import com.example.template.dao.Message;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EnvDataResponse {
    Message message;
}
