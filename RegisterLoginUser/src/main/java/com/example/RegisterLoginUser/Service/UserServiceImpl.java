package com.example.RegisterLoginUser.Service;

import com.example.RegisterLoginUser.DTO.UserDto;
import com.example.RegisterLoginUser.Model.User;
import com.example.RegisterLoginUser.Repository.UserRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.security.Principal;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);

    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findOneById(id);
    }


    public User getActivatedUserFromPrincipal(Principal principal){
        if(principal == null){
            return null;
        }
        User u = userRepository.findByUsername(principal.getName());
        return u;
    }

    @Override
    public UserDto getdetailUser(Principal principal) throws Exception {
        User user = getActivatedUserFromPrincipal(principal);
        if (user == null) {
            throw new Exception("User not found");
        }
        UserDto userDto= new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setName(user.getName());
        return userDto;
    }

/*    @Override
    public List<User> getAllUsers(Integer page, Integer limit, String sort) {
        Pageable paging = PageRequest.of(page, limit, Sort.by("name").ascending());

        Page<User> pagedResult = userRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<User>();
        }
    return null;

    }

 */
  /*  @Override
    public Page<User> getUserPage(@NonNull User pageSetting) {
         new User();
        Sort plantSort = pageSetting.buildSort();
        Pageable plantPage = PageRequest.of(pageSetting.getPage(), pageSetting.getLimit(), plantSort);

        return userRepository.findAll(plantPage);
    }
*/
    @Override
    public List<User> getUsers(String order) {
        if(order=="asc"){
            List<User> user = userRepository.findByOrderByNameAsc();
            return user;
        } else {
            List<User> user = userRepository.findByOrderByNameDesc();
            return user;
        }

    }

}
