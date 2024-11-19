package com.example.HospitalManagement.model;

import jakarta.persistence.*;

@Entity
public class Medic {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long codParafaMedic;
    private String surname;
    private String name;
    private String cnp;
    private int varsta;
    private String email;
    private String username;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodParafaMedic() {
        return codParafaMedic;
    }

    public void setCodParafaMedic(Long codParafaMedic) {
        this.codParafaMedic = codParafaMedic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public enum DepartmentsList {
        Cardiology,
        Endocrinology,
        Neurology,
        Ophthalmology,
        Orthoptic,
        Pediatrics,
        Rheumatology
    }
}
