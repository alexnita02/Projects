package com.example.HospitalManagement.repository.designpattern;

import com.example.HospitalManagement.model.User;
import com.example.HospitalManagement.repository.UserRepositoryStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepositoryStrategy userRepositoryStrategy;

    @Autowired
    public UserService(UserRepositoryStrategy userRepositoryStrategy) {
        this.userRepositoryStrategy = userRepositoryStrategy;
    }

    public User getUserById(Long id) {
        return userRepositoryStrategy.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepositoryStrategy.findAll();
    }

    public void saveUser(User user) {
        userRepositoryStrategy.save(user);
    }
}
