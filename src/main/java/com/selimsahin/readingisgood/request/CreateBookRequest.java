package com.selimsahin.readingisgood.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateBookRequest {
    private String name;
    private Integer stock;
    private BigDecimal price;
}
