package com.selimsahin.readingisgood.database.repository;

import com.selimsahin.readingisgood.database.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
