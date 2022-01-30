package com.selimsahin.readingisgood.controller;

import com.selimsahin.readingisgood.database.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    OrderRepository orderRepository;

//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping("/createItem")
//    public Collection<StatisticResponse> monthlyOrderStatistics() {
//        List<Order> orderList = orderRepository.findAll();
//
//
//        BigDecimal sumPrice = BigDecimal.ZERO;
//        Integer totalBookCount = 0;
//        Integer totalPurcehasedAmount = 0;
//        for (Order order : orderList) {
//            sumPrice = order.getTotalPrice();
//            totalBookCount = order.getOrderedBook().size();
//            for (OrderedBook orderedBook : order.getOrderedBook()) {
//                totalPurcehasedAmount = orderedBook.getAmount();
//            }
//        }
//    }
}
