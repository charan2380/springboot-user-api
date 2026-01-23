package com.example.demo.service.impl;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service("userServiceV2")
public class UserServiceImplV2 implements UserService{
    private final UserRepository repo;
    public UserServiceImplV2(UserRepository repo){ this.repo = repo; }


    public User createUser(User user){
        user.setName(user.getName().toUpperCase());
        return repo.save(user);
    }


    public User getUserById(Long id){
        return repo.findById(id).orElse(null);
    }


    public List<User> getAllUsers(){
        return repo.findAll();
    }


    public User updateUser(Long id, User user){
        User existing = getUserById(id);

        // V2 CHANGE: only name can be updated
        existing.setName(user.getName().toUpperCase());

        // email is NOT updated in v2
        return repo.save(existing);
    }


    public void deleteUser(Long id){
        repo.deleteById(id);
    }
}
