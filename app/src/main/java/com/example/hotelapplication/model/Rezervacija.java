package com.example.hotelapplication.model;


import java.io.Serializable;

public class Rezervacija implements Serializable {

    public int rezervacijaID;
    public Korisnik korisnik;
    public int brojOsoba;
    public int brojSoba;
    public int brojDjece;
    public String datumDolaska;
    public String datumOdlaska;
    public static int id=0;

    public Rezervacija() {
    }

    public Rezervacija( Korisnik korisnik,int brojOsoba, String datumDolaska, String datumOdlaska,int brojSoba,int brojDjece) {
        this.rezervacijaID = id++;

        this.korisnik = korisnik;
        this.brojOsoba = brojOsoba;
        this.datumDolaska = datumDolaska;
        this.datumOdlaska=datumOdlaska;
        this.brojSoba=brojSoba;
        this.brojDjece=brojDjece;
    }



    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }



    public String getDatumDolaska() {
        return datumDolaska;
    }

    public void setDatumDolaska(String datum) {
        this.datumDolaska = datum;
    }
    public String getDatumOdlaska() {
        return getDatumOdlaska();
    }

    public void setDatumOdlaska(String datum) {
        this.datumOdlaska = datum;
    }





    public int getRezervacijaID() {
        return rezervacijaID;
    }

    public void setRezervacijaID(int rezervacijaID) {
        this.rezervacijaID = rezervacijaID;
    }


    public int getBrojOsoba() {
        return brojOsoba;
    }

    public void setBrojOsoba(int brojOsoba) {
        this.brojOsoba = brojOsoba;
    }
    public int getBrojSoba() {
        return brojSoba;
    }

    public void setBrojSoba(int brojSoba) {
        this.brojSoba = brojSoba;
    }


}