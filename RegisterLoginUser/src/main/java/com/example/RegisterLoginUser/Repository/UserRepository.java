package com.example.RegisterLoginUser.Repository;

import com.example.RegisterLoginUser.Model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findOneById(Long id);
    List<User> findByOrderByNameAsc();
    List<User> findByOrderByNameDesc();

}
