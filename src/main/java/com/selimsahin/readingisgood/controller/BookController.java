package com.selimsahin.readingisgood.controller;

import com.selimsahin.readingisgood.database.entity.User;
import com.selimsahin.readingisgood.database.repository.BookRepository;
import com.selimsahin.readingisgood.manager.UserManager;
import com.selimsahin.readingisgood.payload.ApiResponse;
import com.selimsahin.readingisgood.request.CreateBookRequest;
import com.selimsahin.readingisgood.request.UpdateStockRequest;
import com.selimsahin.readingisgood.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping("/book")
public class BookController {

    private static Logger logger = Logger.getLogger((BookController.class).getName());

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserManager userManager;

    @Autowired
    BookService bookService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/createBook")
    public ResponseEntity<?> createBook(@Valid @RequestBody CreateBookRequest createBookRequest) {
        bookService.checkBookNameService(createBookRequest.getName());
        bookService.saveBookService(createBookRequest);
        return ResponseEntity.accepted().body(new ApiResponse(true, "Book created successfully."));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/updateStock")
    public ResponseEntity<?> updateStock(@Valid @RequestBody UpdateStockRequest updateStockRequest) {
        bookService.updateStockService(updateStockRequest.getName(), updateStockRequest.getStock());
        return ResponseEntity.accepted().body(new ApiResponse(true, "Stock updated successfully."));
    }
}
