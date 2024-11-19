package com.example.HospitalManagement.controller;

import com.example.HospitalManagement.exception.UserNotFoundException;
import com.example.HospitalManagement.model.Pacient;
import com.example.HospitalManagement.repository.PacientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class PacientController {

    @Autowired
    private PacientRepository pacientRepository;

    @PostMapping("/pacient")
    Pacient newPacient(@RequestBody Pacient newPacient) {
        return pacientRepository.save(newPacient);
    }

    @GetMapping("/pacients")
    List<Pacient> getAllPacients() {
        return pacientRepository.findAll();
    }

    @GetMapping("/pacient/{id}")
    Pacient getPacientById(@PathVariable Long id) {
        return pacientRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/pacient/{id}")
    Pacient updatePacient(@RequestBody Pacient newPacient, @PathVariable Long id) {
        return pacientRepository.findById(id)
                .map(pacient -> {
                    pacient.setSurname(newPacient.getSurname());
                    pacient.setName(newPacient.getName());
                    pacient.setEmail(newPacient.getEmail());
                    pacient.setCnp(newPacient.getCnp());
                    pacient.setVarsta(newPacient.getVarsta());
                    pacient.setAdresa(newPacient.getAdresa());
                    pacient.setUsername(newPacient.getUsername());
                    pacient.setPassword(newPacient.getPassword());
                    pacient.setMedic(newPacient.getMedic());
                    return pacientRepository.save(pacient);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/pacient/{id}")
    String deletePacient(@PathVariable Long id) {
        if (!pacientRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        pacientRepository.deleteById(id);
        return "Pacient with id " + id + " has been deleted successfully";
    }

}
