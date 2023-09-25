package com.micro.user.service.service;

import com.micro.user.service.entities.User;

import java.util.List;

public interface userService {
    //create
     User saveUser(User user);
     //list All
     List<User>getAllUsers();
    //single user
     User getUser(String userId);
    void deleteUserById(Long userId);
    //delete


}
