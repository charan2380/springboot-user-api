package com.example.demo.service.impl;
import com.example.demo.entity.User;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service("userServiceV2")
public class UserServiceImplV2 implements UserService{
    private final UserRepository repo;
    public UserServiceImplV2(UserRepository repo){ this.repo = repo; }


    @Override
    public User createUser(User user) {

        if (user.getName().length() < 3) {
            throw new IllegalArgumentException("Name must have at least 3 characters");
        }

        user.setName(user.getName().toUpperCase());
        return repo.save(user);
    }



    public User getUserById(Long id){
        return repo.findById(id).orElse(null);
    }


    public List<User> getAllUsers(){
        return repo.findAll();
    }


    @Override
    public User updateUser(Long id, User user) {

        User existing = getUserById(id);

        if (user.getName().length() < 3) {
            throw new IllegalArgumentException("Name must have at least 3 characters");
        }

        existing.setName(user.getName().toUpperCase());
        existing.setEmail(user.getEmail());
        return repo.save(existing);
    }

    // PATCH (partial update)
    public User patchUser(Long id, User user) {

        User existing = getUserById(id);

        // PATCH name
        if (user.getName() != null) {
            if (user.getName().isBlank() || user.getName().length() < 3) {
                throw new IllegalArgumentException("Name must have at least 3 characters");
            }
            existing.setName(user.getName().toUpperCase());
        }

        // PATCH email
        if (user.getEmail() != null) {
            if (repo.existsByEmail(user.getEmail())
                    && !user.getEmail().equals(existing.getEmail())) {
                throw new DuplicateResourceException("Email already exists: " + user.getEmail());
            }
            existing.setEmail(user.getEmail());
        }

        return repo.save(existing);
    }



    public void deleteUser(Long id){
        repo.deleteById(id);
    }
}
