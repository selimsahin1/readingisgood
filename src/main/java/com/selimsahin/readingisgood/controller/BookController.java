package com.selimsahin.readingisgood.controller;

import com.selimsahin.readingisgood.database.entity.Book;
import com.selimsahin.readingisgood.database.repository.BookRepository;
import com.selimsahin.readingisgood.request.CreateBookRequest;
import com.selimsahin.readingisgood.request.UpdateStockRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/createBook")
    public String createBook(@Valid @RequestBody CreateBookRequest createBookRequest) {
        Book book = new Book();
        book.setName(createBookRequest.getName());
        book.setStock(createBookRequest.getStock());
        bookRepository.save(book);

        return "Book added";
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/updateStock")
    public String updateStock(@Valid @RequestBody UpdateStockRequest updateStockRequest) {
        Book book = bookRepository.getByName(updateStockRequest.getName());
        book.setStock(updateStockRequest.getStock());

        bookRepository.save(book);

        return "Stock updated";
    }

}
