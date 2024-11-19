package com.example.HospitalManagement.controller;

import com.example.HospitalManagement.exception.UserNotFoundException;
import com.example.HospitalManagement.model.Programare;
import com.example.HospitalManagement.repository.ProgramareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class ProgramareController {

    @Autowired
    private ProgramareRepository programareRepository;

    @PostMapping("/programare")
    Programare newProgramare(@RequestBody Programare newProgramare) {
        return programareRepository.save(newProgramare);
    }

    @GetMapping("/programari")
    List<Programare> getAllProgramari() {
        return programareRepository.findAll();
    }

    @GetMapping("/programare/{id}")
    Programare getProgramareById(@PathVariable Long id) {
        return programareRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/programare/{id}")
    Programare updateProgramare(@RequestBody Programare newProgramare, @PathVariable Long id) {
        return programareRepository.findById(id)
                .map(programare -> {
                    programare.setDataProgramare(newProgramare.getDataProgramare());
                    programare.setOraProgramare(newProgramare.getOraProgramare());
                    // Update other fields...
                    return programareRepository.save(programare);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/programare/{id}")
    String deleteProgramare(@PathVariable Long id) {
        if (!programareRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        programareRepository.deleteById(id);
        return "Programare with id " + id + " has been deleted successfully";
    }

}
