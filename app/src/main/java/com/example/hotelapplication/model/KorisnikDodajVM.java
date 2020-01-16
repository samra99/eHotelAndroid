package com.example.hotelapplication.model;

import java.io.Serializable;

public class KorisnikDodajVM implements Serializable {
    public String ime;
    public String prezime;
    public String email;
    public String username;
    public String pasos;
    public String password;

    public KorisnikDodajVM() {
    }

    public KorisnikDodajVM(String ime, String prezime, String email, String username, String pasos, String password) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.username = username;
        this.pasos = pasos;
        this.password = password;
    }
}
