package com.example.hotelapplication.model;

import java.io.Serializable;
import java.util.List;

public class SobePregledVM  implements Serializable {

    public static class Row implements Serializable {
        public int Id;


        public String naziv;
        public String opis;
        public String detalji;
    }

    public List<Row> rows;
}
