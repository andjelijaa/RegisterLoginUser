package com.example.RegisterLoginUser.Service;

import com.example.RegisterLoginUser.Model.User;

public interface UserService {
    User findByUsername(String username);
    void save(User user);
}
