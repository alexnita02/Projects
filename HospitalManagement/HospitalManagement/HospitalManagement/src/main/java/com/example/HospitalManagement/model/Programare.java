package com.example.HospitalManagement.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Programare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataProgramare;
    private LocalTime oraProgramare;

    @ManyToOne
    @JoinColumn(name = "medic_id")
    private Medic medic;

    @ManyToOne
    @JoinColumn(name = "pacient_id")
    private Pacient pacient;

    // Constructors, getters, and setters

    // Constructor
    public Programare() {
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataProgramare() {
        return dataProgramare;
    }

    public void setDataProgramare(LocalDate dataProgramare) {
        this.dataProgramare = dataProgramare;
    }

    public LocalTime getOraProgramare() {
        return oraProgramare;
    }

    public void setOraProgramare(LocalTime oraProgramare) {
        this.oraProgramare = oraProgramare;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }
}
