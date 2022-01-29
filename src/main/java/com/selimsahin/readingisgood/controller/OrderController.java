package com.selimsahin.readingisgood.controller;

import com.selimsahin.readingisgood.database.entity.Book;
import com.selimsahin.readingisgood.database.entity.Order;
import com.selimsahin.readingisgood.database.entity.OrderedBook;
import com.selimsahin.readingisgood.database.entity.User;
import com.selimsahin.readingisgood.database.repository.BookRepository;
import com.selimsahin.readingisgood.database.repository.OrderRepository;
import com.selimsahin.readingisgood.database.repository.OrderedBookRepository;
import com.selimsahin.readingisgood.manager.UserManager;
import com.selimsahin.readingisgood.request.CreateOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderedBookRepository orderedBookRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserManager userManager;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/createOrder")
    public String createOrder(CreateOrderRequest createOrderRequest) {
        User user = userManager.getUserFromSecurityContext();

        Date date = new Date();
        BigDecimal totalPrice = BigDecimal.valueOf(0.0);
        for (int i = 0; i <= createOrderRequest.getBookName().size(); i++) {
            OrderedBook orderedBook = new OrderedBook();
            orderedBook.setBookName(createOrderRequest.getBookName().get(i));
            orderedBook.setAmount(createOrderRequest.getQuantity().get(i));

            Book book = bookRepository.getByName(orderedBook.getBookName());
            book.setStock(book.getStock() - createOrderRequest.getQuantity().get(i));

            totalPrice = totalPrice.add(calculateCost(createOrderRequest.getQuantity().get(i), book.getPrice()));
            orderedBookRepository.save(orderedBook);

            bookRepository.save(book);
        }

        Order order = new Order();
        order.setUserId(user.getId());
        order.setDate(date);
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);

        return "Order created";
    }

    public BigDecimal calculateCost(int itemQuantity, BigDecimal itemPrice) {
        BigDecimal totalCost = BigDecimal.ZERO;
        BigDecimal itemCost = itemPrice.multiply(BigDecimal.valueOf(itemQuantity));
        totalCost = totalCost.add(itemCost);
        return totalCost;
    }

    public Order
}
