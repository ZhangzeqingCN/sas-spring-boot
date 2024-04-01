package com.example.template.dto.device;

import com.example.template.dao.Device;
import com.example.template.dao.Control;
import lombok.*;

import java.util.ArrayList;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DataPostResponse {
    Device device;
    ArrayList<Control> controls;
}
