package com.selimsahin.readingisgood.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateOrderRequest {
    @NotNull
    List<String> bookName;
    @Min(1)
    @NotNull
    List<Integer> quantity;
}
