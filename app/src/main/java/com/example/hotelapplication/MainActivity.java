package com.example.hotelapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hotelapplication.helper.MySession;
import com.example.hotelapplication.model.AutentifikacijaResultVM;
import com.example.hotelapplication.model.Korisnik;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AutentifikacijaResultVM x = MySession.getKorisnik();

        if (x==null)
            startActivity(new Intent(this, LoginActivity.class));
        else
            startActivity(new Intent(this, GlavniActivity.class));
    }
}
