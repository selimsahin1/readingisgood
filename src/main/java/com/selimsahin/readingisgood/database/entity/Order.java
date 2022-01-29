package com.selimsahin.readingisgood.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Transactional
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Date date;

    private BigDecimal totalPrice;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<OrderedBook> orderedBook;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderedBook> getOrderedBook() {
        return orderedBook;
    }

    public void setOrderedBook(List<OrderedBook> orderedBook) {
        this.orderedBook = orderedBook;
    }
}
