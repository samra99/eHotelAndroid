package com.example.hotelapplication.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hotelapplication.LoginActivity;
import com.example.hotelapplication.model.AutentifikacijaResultVM;
import com.example.hotelapplication.model.Korisnik;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MySession {
    private static final String PREFS_NAME = "DatotekaZaSharedPrefernces";
    private static String nekikey="Key_korisnik";
    public static AutentifikacijaResultVM getKorisnik()
    {
        SharedPreferences sharedPreferences = MyApp.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String strJson=sharedPreferences.getString(nekikey,"");
        if (strJson.length() == 0)
            return null;

        AutentifikacijaResultVM x = MyGson.build().fromJson(strJson, AutentifikacijaResultVM.class);
        return x;
    }
    public  static  void setKorisnik( AutentifikacijaResultVM k)
    {
        String strGson=k!=null? MyGson.build().toJson(k):" ";

        SharedPreferences sharedPreferences = MyApp.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(nekikey, strGson);
        editor.apply();

    }
}
