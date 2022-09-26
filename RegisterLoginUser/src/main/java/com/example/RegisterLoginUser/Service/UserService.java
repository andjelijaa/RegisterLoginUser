package com.example.RegisterLoginUser.Service;

import com.example.RegisterLoginUser.DTO.UserDto;
import com.example.RegisterLoginUser.Model.User;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.List;

public interface UserService {
    User findByUsername(String username);
    void save(User user);
    User findById(Long id);

    List<User> getUsers(String order);

    UserDto getdetailUser(Principal principal) throws Exception;

   // List<User> getAllUsers(Integer page, Integer limit, String sort);

    //Page<User> getUserPage(User pageSetting);


    }
