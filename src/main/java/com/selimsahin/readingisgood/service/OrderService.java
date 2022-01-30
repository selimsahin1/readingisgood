package com.selimsahin.readingisgood.service;

import com.selimsahin.readingisgood.controller.BookController;
import com.selimsahin.readingisgood.database.entity.Book;
import com.selimsahin.readingisgood.database.entity.OrderedBook;
import com.selimsahin.readingisgood.database.entity.Orders;
import com.selimsahin.readingisgood.database.entity.User;
import com.selimsahin.readingisgood.database.repository.BookRepository;
import com.selimsahin.readingisgood.database.repository.OrderRepository;
import com.selimsahin.readingisgood.database.repository.OrderedBookRepository;
import com.selimsahin.readingisgood.exception.ApiRequestException;
import com.selimsahin.readingisgood.manager.UserManager;
import com.selimsahin.readingisgood.request.CreateOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderedBookRepository orderedBookRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserManager userManager;

    private static final Logger logger = Logger.getLogger((BookController.class).getName());
    private final Date date = new Date();

    public Orders saveOrders() {
        User user = userManager.getUserFromSecurityContext();

        Orders orders = new Orders();
        orders.setUserId(user.getId());
        orders.setDate(date);
        try {
            orderRepository.save(orders);
            return orders;
        } catch (Exception e) {
            logger.log(
                    Level.WARNING,
                    user.getUsername() + " can not create an order.");
            throw new ApiRequestException("Order can not created.");
        }
    }

    public void saveOrderedBook(CreateOrderRequest createOrderRequest, Orders orders) {
        User user = userManager.getUserFromSecurityContext();

        for (int i = 0; i <= createOrderRequest.getBookName().size() - 1; i++) {
            OrderedBook orderedBook = new OrderedBook();
            orderedBook.setBookName(createOrderRequest.getBookName().get(i));
            orderedBook.setAmount(createOrderRequest.getQuantity().get(i));
            orderedBook.setOrders(orders);

            Book book = bookRepository.getByName(orderedBook.getBookName())
                    .orElseThrow(() -> {
                        logger.log(
                                Level.WARNING,
                                user.getUsername() + " can not create an order, "
                                        + orderedBook.getBookName() + "does not exist.");
                        throw new ApiRequestException("Book can not be found.");
                    });
            book.setStock(book.getStock() - createOrderRequest.getQuantity().get(i));

//            totalPrice = totalPrice.add(calculateCost(createOrderRequest.getQuantity().get(i), book.getPrice()));
            try {
                orderedBookRepository.save(orderedBook);
            } catch (Exception e) {
                logger.log(
                        Level.WARNING,
                        user.getUsername() + " can not create an order, "
                                + orderedBook.getBookName() + "can not saved.");
                throw new ApiRequestException("Can not order the book.");
            }

            try {
                bookRepository.save(book);
            } catch (Exception e) {
                logger.log(
                        Level.WARNING,
                        user.getUsername() + " can not create an order, "
                                + orderedBook.getBookName() + " stock can not updated.");
                throw new ApiRequestException("Stock can not updated.");
            }
        }
    }

    public Orders queryOrderService(Long id) {
        User user = userManager.getUserFromSecurityContext();

        return orderRepository.getByIdAndUserId(id, user.getId()).orElseThrow(() -> {
            logger.log(
                    Level.WARNING,
                    user.getUsername() + " can not query order");
            throw new ApiRequestException("Stock can not updated.");
        });
    }

    public List<Orders> queryOrderByDateService(Date from, Date to) {
        User user = userManager.getUserFromSecurityContext();

        return orderRepository.getAllByDateBetweenAndUserId(from, to, user.getId()).orElseThrow(() -> {
            logger.log(
                    Level.WARNING,
                    user.getUsername() + " can not query order between " + from + " " + to);
            throw new ApiRequestException("Query Order has failed.");
        });
    }
}
