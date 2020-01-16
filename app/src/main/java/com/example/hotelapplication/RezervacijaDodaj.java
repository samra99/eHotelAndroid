package com.example.hotelapplication;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hotelapplication.fragments.DatePickerDialogFragment;
import com.example.hotelapplication.helper.MyConfig;
import com.example.hotelapplication.helper.MyGson;
import com.example.hotelapplication.helper.MyUrlConnection;
import com.example.hotelapplication.model.Rezervacija;
import com.example.hotelapplication.model.RezervacijaPostVM;
import com.example.hotelapplication.model.SobePregledVM;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RezervacijaDodaj extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    private static final String SOBA_KEY = "soba_key";
    private SobePregledVM.Row soba;
    private EditText DateFrom;
    private EditText DateTO;
    private EditText BrojSoba;
    private EditText BrojOsoba;
    private EditText BrojDjece;
    private ImageView DateFromIV;
    private ImageView DateToIV;
    private Button spremiRezervaciju;

    private int DatePosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_rezervacija_add);
        DatePosition = 0;
        DateFromIV = findViewById(R.id.btnDatePicker);
        DateToIV = findViewById(R.id.btnDatePicker1);
        BrojDjece = findViewById(R.id.txtBrojDjece);
        BrojSoba = findViewById(R.id.txtBrojSoba);
        BrojOsoba = findViewById(R.id.txtBrojOsoba);
        DateFrom = findViewById(R.id.txtDatumDolaska);
        DateTO =findViewById(R.id.txtDatumOdlaska);

        spremiRezervaciju =findViewById(R.id.btnSnimiRezervaciju);

        DateFromIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePosition = 1;
                DatePickerDialogFragment dialogFragment = new DatePickerDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "date picker");
            }
        });
        DateToIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePosition = 2;
                DatePickerDialogFragment dialogFragment = new DatePickerDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "date picker");
            }
        });
        spremiRezervaciju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RezervacijaPostVM postVM = new RezervacijaPostVM();
                if (TextUtils.isEmpty(BrojOsoba.getText()) ||
                        TextUtils.isEmpty(BrojDjece.getText()) ||
                        TextUtils.isEmpty(BrojSoba.getText()) ||
                        TextUtils.isEmpty(DateFrom.getText()) ||
                        TextUtils.isEmpty(DateTO.getText()) ) {
                    Toast.makeText(RezervacijaDodaj.this, "Sva polja moraju biti popunjena!", Toast.LENGTH_SHORT).show();
                  
                }else{
                    postVM.BrojDjece = Integer.parseInt(BrojDjece.getText().toString());
                    postVM.BrojOsoba = Integer.parseInt(BrojOsoba.getText().toString());
                    postVM.BrojSoba = Integer.parseInt(BrojSoba.getText().toString());
                    postVM.datumDolaska = DateFrom.getText().toString();
                    postVM.datumOdlaska = DateTO.getText().toString();
                    postRezervacijuTask(postVM);
                }
            }
        });
    }

    private void postRezervacijuTask(final RezervacijaPostVM postVM) {

        new AsyncTask<Void, Void, RezervacijaPostVM>() {

            @Override
            protected void onPreExecute() {
            }


            @Override
            protected RezervacijaPostVM doInBackground(Void... voids) {

                String strGson=postVM!=null? MyGson.build().toJson(postVM):" ";

                String strJson = MyUrlConnection.Post(MyConfig.baseUrl + "api/Rezervacija/post",strGson);
                RezervacijaPostVM x = MyGson.build().fromJson(strJson, RezervacijaPostVM.class);
                return x;
            }

            @Override
            protected void onPostExecute(RezervacijaPostVM x) {
                if (x!=null)
                {
                    Toast.makeText(RezervacijaDodaj.this, "Rezervacija uspjesno dodana", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        }.execute();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = calendar.getTime();

        if (DatePosition == 1){
            DateFrom.setText(sdf.format(date));

        }
        if (DatePosition == 2)
        {
            DateTO.setText(sdf.format(date));
        }

    }
}
