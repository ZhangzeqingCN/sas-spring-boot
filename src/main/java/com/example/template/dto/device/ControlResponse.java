package com.example.template.dto.device;

import com.example.template.dao.Message;
import lombok.*;

@Builder
@Setter
@Getter
@ToString
public class ControlResponse  {
    Message message;
}
