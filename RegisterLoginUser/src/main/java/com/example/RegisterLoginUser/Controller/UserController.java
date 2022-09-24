package com.example.RegisterLoginUser.Controller;


import com.example.RegisterLoginUser.Config.JwtUtils;
import com.example.RegisterLoginUser.Model.RequestUser;
import com.example.RegisterLoginUser.Model.User;
import com.example.RegisterLoginUser.Repository.UserRepository;
import com.example.RegisterLoginUser.Service.UserService;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public boolean register(@RequestBody User user) throws MessagingException {
        User u = userRepository.findByUsername(user.getUsername());
        if (u == null) {
            userService.save(user);
            return true;
        }
        return false;
    }

    @PostMapping("/login")
    public String login(@RequestBody RequestUser requestUser) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestUser.getUsername(), requestUser.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username or/and password");
        }
        return jwtUtils.generateToken(requestUser.getUsername());
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public User getUserDetail(@PathVariable Long id){
        return userService.findById(id);
    }



}