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
public class Position {
    Float x;
    Float y;
}
