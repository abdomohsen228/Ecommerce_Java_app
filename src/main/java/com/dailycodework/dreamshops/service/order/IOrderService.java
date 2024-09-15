package com.dailycodework.dreamshops.service.order;

import com.dailycodework.dreamshops.dto.OrderDto;
import com.dailycodework.dreamshops.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderService {
    Order placeHolder(Long userId);
    OrderDto getOrder(Long orderId);


    List<OrderDto> getUserOrders(Long userId);
}
