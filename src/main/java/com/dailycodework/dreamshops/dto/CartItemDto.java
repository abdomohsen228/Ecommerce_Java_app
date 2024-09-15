package com.dailycodework.dreamshops.dto;



import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {
    private Long itemId;
    private BigDecimal price;
    private BigDecimal quantity;
    private ProductDto product;
}
