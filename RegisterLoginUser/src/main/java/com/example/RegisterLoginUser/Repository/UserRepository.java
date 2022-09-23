package com.example.RegisterLoginUser.Repository;

import com.example.RegisterLoginUser.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
