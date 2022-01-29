package com.selimsahin.readingisgood.database.repository;

import com.selimsahin.readingisgood.database.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book getByName(String name);
}
