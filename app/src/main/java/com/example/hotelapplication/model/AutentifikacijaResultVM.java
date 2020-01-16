package com.example.hotelapplication.model;

import java.io.Serializable;

public class AutentifikacijaResultVM implements Serializable
{
    public int korisnikId;
    public String username;
    public String ime;
    public String prezime;
    public String token;
    public String mail;
    public String password;

    public Integer korisnickiNalogId;
}