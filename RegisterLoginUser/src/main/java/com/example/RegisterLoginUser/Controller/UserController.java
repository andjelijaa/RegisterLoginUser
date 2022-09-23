package com.example.RegisterLoginUser.Controller;


import com.example.RegisterLoginUser.Config.JwtUtils;
import com.example.RegisterLoginUser.Model.User;
import com.example.RegisterLoginUser.Repository.UserRepository;
import com.example.RegisterLoginUser.Service.UserService;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
        }

    @PostMapping("/register")
    public boolean register(@RequestBody User user) throws MessagingException {
        User u = userRepository.findByUsername(user.getUsername());
        if(u == null){
            userService.save(user);
            return true;
        }
        return false;
    }



}

