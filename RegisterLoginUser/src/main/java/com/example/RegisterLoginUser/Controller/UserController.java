package com.example.RegisterLoginUser.Controller;



import com.example.RegisterLoginUser.Config.JwtUtils;
import com.example.RegisterLoginUser.DTO.PageDTO;
import com.example.RegisterLoginUser.DTO.UserDto;
import com.example.RegisterLoginUser.Model.PageToPageDTOMapper;
import com.example.RegisterLoginUser.Model.RequestUser;
import com.example.RegisterLoginUser.Model.User;
import com.example.RegisterLoginUser.Repository.UserRepository;
import com.example.RegisterLoginUser.Service.UserService;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PageToPageDTOMapper pageToPageDTOMapper;

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



    @GetMapping(value = "/me",produces = "application/json")
    public UserDto getUser(Principal principal) throws Exception {
        return userService.getdetailUser(principal);
    }

/*    @GetMapping(value = "/{order}",produces = "application/json")
    public List<User> getUserSort(@PathVariable String order){

        return userService.getUsers(order);

    }

   @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "asc") String sort)
    {
        List<User> list = userService.getAllUsers(page, limit, sort);

        return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/users")
    public PageDTO<User> getUserPage(User pageSettings) {

        log.info(
                "Request for plant page received with data : " + pageSettings);

        return pageToPageDTOMapper.pageToPageDTO(userService.getUserPage(pageSettings));
    }*/
}