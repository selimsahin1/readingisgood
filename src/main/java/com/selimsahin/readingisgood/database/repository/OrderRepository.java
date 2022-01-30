package com.selimsahin.readingisgood.database.repository;

import com.selimsahin.readingisgood.database.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    Optional<Orders> getByIdAndUserId(Long id, Long userId);

    Optional<List<Orders>> getAllByDateBetweenAndUserId(Date from, Date to, Long userId);

    List<Orders> getAllByDateBetween(Date from, Date to);

}
