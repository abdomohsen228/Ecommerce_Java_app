package com.dailycodework.dreamshops.service.order;

import com.dailycodework.dreamshops.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderService {
    Order placeHolder(Long userId);
    Order getOrder(Long orderId);


    List<Order> getUserOrders(Long userId);
}
