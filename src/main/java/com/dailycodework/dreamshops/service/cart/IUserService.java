package com.dailycodework.dreamshops.service.cart;

import com.dailycodework.dreamshops.request.CreateUserRequest;
import com.dailycodework.dreamshops.request.UserUpdateRequest;
import org.apache.catalina.User;

public interface IUserService {
    User getUserById(Long userId);
    User createUser(CreateUserRequest request );
    User updateUser(UserUpdateRequest request , Long userId);
    void deleteUser(Long userId);

}
