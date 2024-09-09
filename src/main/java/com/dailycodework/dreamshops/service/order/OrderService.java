package com.dailycodework.dreamshops.service.order;

import com.dailycodework.dreamshops.enums.OrderStatus;
import com.dailycodework.dreamshops.model.Cart;
import com.dailycodework.dreamshops.model.Order;
import com.dailycodework.dreamshops.model.OrderItem;
import com.dailycodework.dreamshops.model.Product;
import com.dailycodework.dreamshops.repository.OrderRepository;
import com.dailycodework.dreamshops.repository.ProductRepository;
import com.dailycodework.dreamshops.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;


    @Override
    public Order placeHolder(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);

        Order order = createOrder(cart);

        List<OrderItem> orderItemList = createOrderItems(order, cart);

        order.setOrderItems(new HashSet<>(orderItemList));

        order.setTotalAmount(calculateTotalAmount(orderItemList));
        Order savedOrder = orderRepository.save(order);

        cartService.clearCart(cart.getId());

        return savedOrder;
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException("Order not found"));
    }

    private Order createOrder(Cart cart) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderedDate(LocalDate.now());
        return order;


    }
    private List<OrderItem> createOrderItems(Order order , Cart cart) {
        return cart.getItems().stream().map(cartItem ->{
            Product product = cartItem.getProduct();
            product.setInventory(product.getInventory() - cartItem.getQuantity());
           productRepository.save(product);
           return new OrderItem(
                   order,
                   product,
                   cartItem.getQuantity(),
                   cartItem.getUnitPrice());
        }).toList();
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItemlist) {
        return orderItemlist.stream().
                map(item -> item.getPrice()
                        .multiply(new BigDecimal(item.getQuantity())))
                         .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);

    }


}
