package com.example.HospitalManagement.controller;

import com.example.HospitalManagement.exception.UserNotFoundException;
import com.example.HospitalManagement.model.DetaliiMedicale;
import com.example.HospitalManagement.repository.DetaliiMedicaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class DetaliiMedicaleController {

    @Autowired
    private DetaliiMedicaleRepository detaliiMedicaleRepository;

    @PostMapping("/detaliimedicale")
    DetaliiMedicale newDetaliiMedicale(@RequestBody DetaliiMedicale newDetaliiMedicale) {
        return detaliiMedicaleRepository.save(newDetaliiMedicale);
    }

    @GetMapping("/detaliimedicales")
    List<DetaliiMedicale> getAllDetaliiMedicale() {
        return detaliiMedicaleRepository.findAll();
    }

    @GetMapping("/detaliimedicale/{id}")
    DetaliiMedicale getDetaliiMedicaleById(@PathVariable Long id) {
        return detaliiMedicaleRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/detaliimedicale/{id}")
    DetaliiMedicale updateDetaliiMedicale(@RequestBody DetaliiMedicale newDetaliiMedicale, @PathVariable Long id) {
        return detaliiMedicaleRepository.findById(id)
                .map(detaliiMedicale -> {
                    detaliiMedicale.setTemperatura(newDetaliiMedicale.getTemperatura());
                    detaliiMedicale.setTensiune(newDetaliiMedicale.getTensiune());
                    detaliiMedicale.setBoliCronice(newDetaliiMedicale.getBoliCronice());
                    // Update other fields...
                    return detaliiMedicaleRepository.save(detaliiMedicale);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/detaliimedicale/{id}")
    String deleteDetaliiMedicale(@PathVariable Long id) {
        if (!detaliiMedicaleRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        detaliiMedicaleRepository.deleteById(id);
        return "DetaliiMedicale with id " + id + " has been deleted successfully";
    }
}
