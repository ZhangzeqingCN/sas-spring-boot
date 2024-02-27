package com.example.template.dao.embed;

import jakarta.persistence.Embeddable;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Embeddable
public class MACAddress {
    Byte u1;
    Byte u2;
    Byte u3;
    Byte u4;
    Byte u5;
    Byte u6;
}
