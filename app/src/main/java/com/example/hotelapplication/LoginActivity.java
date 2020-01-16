package com.example.hotelapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hotelapplication.helper.MyConfig;
import com.example.hotelapplication.helper.MyGson;
import com.example.hotelapplication.helper.MySession;
import com.example.hotelapplication.helper.MyUrlConnection;
import com.example.hotelapplication.model.AutentifikacijaLoginPostVM;
import com.example.hotelapplication.model.AutentifikacijaResultVM;
import com.example.hotelapplication.model.Korisnik;

public class LoginActivity extends AppCompatActivity {


    private EditText txtUsername;
    private EditText txtPassword;
    private TextView tvRegistracija;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        tvRegistracija = findViewById(R.id.goToReg);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnLoginClick();
            }
        });

        tvRegistracija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegistracijaActivity.class));

            }
        });

    }

    private void do_btnLoginClick() {

        String strUsername = txtUsername.getText().toString();
        String strPassword = txtPassword.getText().toString();

        //Korisnik x = Storage.LoginCheck(strUsername, strPassword);
         UserLoginTask(strUsername,strPassword);



    }

    private void UserLoginTask(final String strUsername, final String strPassword) {

         new AsyncTask<Void, Void, AutentifikacijaResultVM>() {
             private ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                progressDialog = ProgressDialog.show(LoginActivity.this, "Loading", "Sačekajte...");

            }


            @Override
            protected AutentifikacijaResultVM doInBackground(Void... voids) {

                AutentifikacijaLoginPostVM loginPostVM = new AutentifikacijaLoginPostVM(strUsername,strPassword,"");
                String strGson=loginPostVM!=null? MyGson.build().toJson(loginPostVM):" ";

                String strJson = MyUrlConnection.Post(MyConfig.baseUrl + "api/Autentifikacija/LoginCheck",strGson);
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

        if (x == null)
        {
            View parentLayout = findViewById(android.R.id.content);
            Snackbar.make(parentLayout, "Pogrešan username/password", Snackbar.LENGTH_LONG).show();
        }
        else
        {
            MySession.setKorisnik(x);
            startActivity(new Intent(this, GlavniActivity.class));
        }
    }
}
