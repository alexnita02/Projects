package com.example.HospitalManagement.model;

import jakarta.persistence.*;
@Entity
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String role;

        private Long  codParafaMedic;

        private String surname;
        private String name;




    @Column(unique = true)
        private String cnp;

        @Column(unique = true)
        private String email;

        private String adresa;
        private int varsta;

        private String username;

        private String password;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

        public String getEmail() {
            return email;
        }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getCodParafaMedic() {
        return codParafaMedic;
    }

    public void setCodParafaMedic(Long codParafaMedic) {
        this.codParafaMedic = codParafaMedic;
    }
    public void setEmail(String email) {
            this.email = email;
        }

        public String getAdresa() {
            return adresa;
        }

        public void setAdresa(String adresa) {
            this.adresa = adresa;
        }

        public int getVarsta() {
            return varsta;
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

    public void setVarsta(int varsta) {
            this.varsta = varsta;
        }


}
