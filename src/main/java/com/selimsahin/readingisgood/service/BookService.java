package com.selimsahin.readingisgood.service;

import com.selimsahin.readingisgood.controller.BookController;
import com.selimsahin.readingisgood.database.entity.Book;
import com.selimsahin.readingisgood.database.entity.User;
import com.selimsahin.readingisgood.database.repository.BookRepository;
import com.selimsahin.readingisgood.exception.ApiRequestException;
import com.selimsahin.readingisgood.exception.AppException;
import com.selimsahin.readingisgood.manager.UserManager;
import com.selimsahin.readingisgood.request.CreateBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserManager userManager;

    private static Logger logger = Logger.getLogger((BookController.class).getName());


    public void checkBookNameService(String bookName) {
        if (bookRepository.getByName(bookName).isPresent()) {
            logger.log(Level.WARNING, bookName + " named book already defined.");
            throw new ApiRequestException("Book already defined.");
        }
    }

    public void saveBookService(CreateBookRequest createBookRequest) {
        Book book = new Book();
        book.setName(createBookRequest.getName());
        book.setStock(createBookRequest.getStock());
        book.setPrice(createBookRequest.getPrice());
        try {
            bookRepository.save(book);
            logger.log(Level.FINE, " is created a Book named ");
        } catch (Exception e) {
            logger.log(Level.WARNING, " can not create a book");
            throw new ApiRequestException("Book can not created.");
        }
    }

    public void updateStockService(String bookName, Integer stock) {
        User user = userManager.getUserFromSecurityContext();
        Book book = bookRepository.getByName(bookName)
                .orElseThrow(() -> new AppException("Book does not exist."));
        book.setStock(stock);
        try {
            bookRepository.save(book);
            logger.log(Level.FINE, user.getUsername() + " is created a Book named " + book.getName());
        } catch (Exception e) {
            logger.log(Level.WARNING, user.getUsername() + " can not create a book");
            throw new ApiRequestException("Stock can not updated.");
        }
    }
}
