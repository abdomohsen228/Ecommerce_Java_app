package com.dailycodework.dreamshops.dto;

import com.dailycodework.dreamshops.model.Cart;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String Firstname;
    private String Lastname;
    private String email;
    private List<OrderDto> orders;
    private CartDto cart;

}
