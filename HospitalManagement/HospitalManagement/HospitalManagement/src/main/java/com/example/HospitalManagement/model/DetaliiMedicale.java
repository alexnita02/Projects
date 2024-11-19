package com.example.HospitalManagement.model;

import jakarta.persistence.*;

@Entity
public class DetaliiMedicale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double temperatura;
    private String tensiune;
    private String boliCronice;

    @ManyToOne
    @JoinColumn(name = "pacient_id")
    private Pacient pacient;


    // Constructor
    public DetaliiMedicale() {
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public String getTensiune() {
        return tensiune;
    }

    public void setTensiune(String tensiune) {
        this.tensiune = tensiune;
    }

    public String getBoliCronice() {
        return boliCronice;
    }

    public void setBoliCronice(String boliCronice) {
        this.boliCronice = boliCronice;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }
}
