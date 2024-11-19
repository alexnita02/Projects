package com.example.HospitalManagement.repository;

import com.example.HospitalManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
