package com.github.komidawi.pccserver.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
public class Pizza {

    @Id
    @GeneratedValue
    private Long id;

    private UUID uuid = UUID.randomUUID();

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal size;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal price;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal ratio;

}
