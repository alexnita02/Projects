package com.example.HospitalManagement.repository;

import com.example.HospitalManagement.model.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientRepository extends JpaRepository<Pacient,Long> {

}
