package com.example.template.dto.udp;

import com.example.template.dao.Message;
import lombok.*;

import java.util.ArrayList;

@SuppressWarnings("unused")
@Builder
@Setter
@Getter
@ToString
public class UdpPost {
    final ArrayList<Message> messages = new ArrayList<>();
}
