package com.example.template.dto.device;

import com.example.template.dao.Device;
import com.example.template.dao.Message;
import lombok.*;

import java.util.ArrayList;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DataPostRequest {
    Device device;
    ArrayList<Message> messages;
}
