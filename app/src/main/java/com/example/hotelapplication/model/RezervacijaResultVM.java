package com.example.hotelapplication.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RezervacijaResultVM implements Serializable {



    public static class Row implements Serializable {
        public int id;
        public String datumDolaska;
        public String datumOdlaska;
        public String korsnik;
        public int brojOsoba;
        public int brojDjece;
        public int brojSoba ;

    }

    public List<Row> rows;





}
