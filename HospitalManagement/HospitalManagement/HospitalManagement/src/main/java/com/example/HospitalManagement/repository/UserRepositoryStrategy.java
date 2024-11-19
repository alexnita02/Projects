package com.example.HospitalManagement.repository;

import com.example.HospitalManagement.model.User;

import java.util.List;

public interface UserRepositoryStrategy {
    User findById(Long id);
    List<User> findAll();
    void save(User user);
}
