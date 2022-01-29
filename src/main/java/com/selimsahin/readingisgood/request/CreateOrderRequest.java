package com.selimsahin.readingisgood.request;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CreateOrderRequest {
    List<String> bookName;
    @NotNull
    List<Integer> quantity;

    public List<String> getBookName() {
        return bookName;
    }

    public void setBookName(List<String> bookName) {
        this.bookName = bookName;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }
}
