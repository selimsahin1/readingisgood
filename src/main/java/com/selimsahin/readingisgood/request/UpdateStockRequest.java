package com.selimsahin.readingisgood.request;

import lombok.Data;

@Data
public class UpdateStockRequest {
    String name;
    Integer stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
