package com.example.hotelapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hotelapplication.helper.MyConfig;
import com.example.hotelapplication.helper.MyGson;
import com.example.hotelapplication.helper.MySession;
import com.example.hotelapplication.helper.MyUrlConnection;
import com.example.hotelapplication.model.AutentifikacijaLoginPostVM;
import com.example.hotelapplication.model.AutentifikacijaResultVM;
import com.example.hotelapplication.model.KorisnikDodajVM;

public class RegistracijaActivity extends AppCompatActivity {

    private EditText ET_Ime;
    private EditText ET_Prezime;
    private EditText ET_Pasos;
    private EditText ET_KorisnickoIme;
    private EditText ET_Email;
    private EditText ET_Lozinka;
    private Button btn_Registriraj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);

        btn_Registriraj = findViewById(R.id.btnRegistriraj);
        ET_Ime = findViewById(R.id.txtIme);
        ET_Prezime = findViewById(R.id.txtPrezime);
        ET_Pasos = findViewById(R.id.txtPasos);
        ET_Email = findViewById(R.id.txtEmail);
        ET_Lozinka = findViewById(R.id.txtLozinka);
        ET_KorisnickoIme = findViewById(R.id.txtKorisnickoIme);

        btn_Registriraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KorisnikDodajVM dodajVM = new KorisnikDodajVM();

                if (TextUtils.isEmpty(ET_Ime.getText()) ||
                        TextUtils.isEmpty(ET_Prezime.getText()) ||
                        TextUtils.isEmpty(ET_Email.getText()) ||
                        TextUtils.isEmpty(ET_Lozinka.getText()) ||
                        TextUtils.isEmpty(ET_Pasos.getText()) ||
                        TextUtils.isEmpty(ET_KorisnickoIme.getText())) {
                    Toast.makeText(RegistracijaActivity.this, "Sva polja moraju biti popunjena!", Toast.LENGTH_SHORT).show();

                } else {


                    dodajVM.ime = ET_Ime.getText().toString();
                    dodajVM.prezime = ET_Prezime.getText().toString();
                    dodajVM.email = ET_Email.getText().toString();
                    dodajVM.pasos = ET_Pasos.getText().toString();
                    dodajVM.username = ET_KorisnickoIme.getText().toString();
                    dodajVM.password = ET_Lozinka.getText().toString();
                    dodajKorisnikaTask(dodajVM);
                }
            }
        });
    }

    private void dodajKorisnikaTask(final KorisnikDodajVM dodajVM) {
        new AsyncTask<Void, Void, AutentifikacijaResultVM>() {
            private ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                progressDialog = ProgressDialog.show(RegistracijaActivity.this, "Loading", "Sačekajte...");

            }


            @Override
            protected AutentifikacijaResultVM doInBackground(Void... voids) {

                String strGson = dodajVM != null ? MyGson.build().toJson(dodajVM) : " ";

                String strJson = MyUrlConnection.Post(MyConfig.baseUrl + "api/Korisnik/post", strGson);
                AutentifikacijaResultVM x = MyGson.build().fromJson(strJson, AutentifikacijaResultVM.class);
                return x;
            }

            @Override
            protected void onPostExecute(AutentifikacijaResultVM x) {
                progressDialog.dismiss();

                UserLogin(x);
            }
        }.execute();
    }

    private void UserLogin(AutentifikacijaResultVM x) {

        if (x == null) {
            View parentLayout = findViewById(android.R.id.content);
            Snackbar.make(parentLayout, "Pogrešan username/password", Snackbar.LENGTH_LONG).show();
        } else {
            MySession.setKorisnik(x);
            startActivity(new Intent(this, GlavniActivity.class));
        }
    }
}
