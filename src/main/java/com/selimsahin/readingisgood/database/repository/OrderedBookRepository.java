package com.selimsahin.readingisgood.database.repository;

import com.selimsahin.readingisgood.database.entity.OrderedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedBookRepository extends JpaRepository<OrderedBook, Long> {
}
