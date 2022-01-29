package com.selimsahin.readingisgood.request;

public class CreateBookRequest {
    private String name;
    private Integer stock;

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
