package com.example.HospitalManagement.controller;

import com.example.HospitalManagement.exception.UserNotFoundException;
import com.example.HospitalManagement.model.Medic;
import com.example.HospitalManagement.repository.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class MedicController {

    @Autowired
    private MedicRepository medicRepository;

    @PostMapping("/medic")
    Medic newMedic(@RequestBody Medic newMedic) {
        return medicRepository.save(newMedic);
    }

    @GetMapping("/medics")
    List<Medic> getAllMedics() {
        return medicRepository.findAll();
    }

    @GetMapping("/medic/{id}")
    Medic getMedicById(@PathVariable Long id) {
        return medicRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/medic/{id}")
    Medic updateMedic(@RequestBody Medic newMedic, @PathVariable Long id) {
        return medicRepository.findById(id)
                .map(medic -> {
                    medic.setCodParafaMedic(newMedic.getCodParafaMedic());
                    medic.setSurname(newMedic.getSurname());
                    medic.setName(newMedic.getName());
                    medic.setCnp(newMedic.getCnp());
                    medic.setVarsta(newMedic.getVarsta());
                    medic.setEmail(newMedic.getEmail());
                    medic.setUsername(newMedic.getUsername());
                    medic.setPassword(newMedic.getPassword());
                    return medicRepository.save(medic);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/medic/{id}")
    String deleteMedic(@PathVariable Long id) {
        if (!medicRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        medicRepository.deleteById(id);
        return "Medic with id " + id + " has been deleted successfully";
    }
}
