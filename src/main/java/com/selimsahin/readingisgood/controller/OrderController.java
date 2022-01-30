package com.selimsahin.readingisgood.controller;

import com.selimsahin.readingisgood.database.entity.Orders;
import com.selimsahin.readingisgood.database.repository.BookRepository;
import com.selimsahin.readingisgood.database.repository.OrderRepository;
import com.selimsahin.readingisgood.database.repository.OrderedBookRepository;
import com.selimsahin.readingisgood.manager.UserManager;
import com.selimsahin.readingisgood.payload.ApiResponse;
import com.selimsahin.readingisgood.request.CreateOrderRequest;
import com.selimsahin.readingisgood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static Logger logger = Logger.getLogger((BookController.class).getName());

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderedBookRepository orderedBookRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserManager userManager;

    @Autowired
    OrderService orderService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        Orders orders = orderService.saveOrders();
        orderService.saveOrderedBook(createOrderRequest, orders);

        return ResponseEntity.accepted().body(new ApiResponse(true, "Order created successfully."));
    }

//    public BigDecimal calculateCost(int itemQuantity, BigDecimal itemPrice) {
//        BigDecimal totalCost = BigDecimal.ZERO;
//        BigDecimal itemCost = itemPrice.multiply(BigDecimal.valueOf(itemQuantity));
//        totalCost = totalCost.add(itemCost);
//        return totalCost;
//    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/queryOrder")
    public Orders queryOrder(@RequestParam("orderId") Long id) {
        return orderService.queryOrderService(id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/queryOrderByDate")
    public List<Orders> queryOrderByDate(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date from,
                                         @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date to) {
        return orderService.queryOrderByDateService(from, to);
    }
}
