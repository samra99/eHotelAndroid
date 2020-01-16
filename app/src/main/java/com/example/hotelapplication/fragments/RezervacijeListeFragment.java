package com.example.hotelapplication.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelapplication.R;
import com.example.hotelapplication.RezervacijaDodaj;
import com.example.hotelapplication.helper.MyConfig;
import com.example.hotelapplication.helper.MyFragmentUtils;
import com.example.hotelapplication.helper.MyGson;
import com.example.hotelapplication.helper.MyUrlConnection;
import com.example.hotelapplication.model.Rezervacija;
import com.example.hotelapplication.model.RezervacijaDeleteVM;
import com.example.hotelapplication.model.RezervacijaResultVM;
import com.example.hotelapplication.model.Storage;

import java.util.Date;
import java.util.List;


public class RezervacijeListeFragment extends Fragment {


    private ListView lvRezervacije;
    private FloatingActionButton btnNovaRezervacija;
    private BaseAdapter adapter;

    public RezervacijeListeFragment() {
        // Required empty public constructor
    }

    public static RezervacijeListeFragment newInstance() {
        RezervacijeListeFragment fragment = new RezervacijeListeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rezervacija_list, container, false);
        lvRezervacije = view.findViewById(R.id.lvRezervacije);
        btnNovaRezervacija = view.findViewById(R.id.fab);
        btnNovaRezervacija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO rijesiti button
               // MyFragmentUtils.openAsReplace(getActivity(),R.id.mjestoFragment,RezervacijaDodajFragment.newInstance());
                Intent i = new Intent(getActivity(), RezervacijaDodaj.class);
                startActivity(i);
            }
        });
        popuniPodatkeTask();

        return view;
    }

    private void popuniPodatkeTask() {
        new AsyncTask<Void, Void, RezervacijaResultVM>() {
            private ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                progressDialog = ProgressDialog.show(getActivity(), "Loading", "Sačekajte...");
            }


            @Override
            protected RezervacijaResultVM doInBackground(Void... voids) {

                String strJson = MyUrlConnection.Get(MyConfig.baseUrl + "api/Rezervacija/get");
                RezervacijaResultVM x = MyGson.build().fromJson(strJson, RezervacijaResultVM.class);
                return x;
            }

            @Override
            protected void onPostExecute(RezervacijaResultVM x) {

                progressDialog.dismiss();
                popuniPodatke(x);
            }
        }.execute();
    }

    private void popuniPodatke(final RezervacijaResultVM podaci) {

        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return podaci.rows.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent) {
                if (view == null) {
                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.rezervacija_stavka, parent, false);
                }

                TextView txtFirstLine = view.findViewById(R.id.txtFirstLine_1);
                TextView txtSecondLine = view.findViewById(R.id.txtSecondLine_1);
                TextView txtThirdLine = view.findViewById(R.id.txtThirdLine_1);

                RezervacijaResultVM.Row x = podaci.rows.get(position);
                //TODO rijesiti

                String[] s1 = x.datumDolaska.split(" ")[0].split("-");
                String[] s2 = x.datumOdlaska.split(" ")[0].split("-");
                txtFirstLine.setText("Od " + s1[2] + "." + s1[1] + "." + s1[0]);
                txtSecondLine.setText("Do " + s2[2] + "." + s2[1] + "." + s2[0]);
                txtThirdLine.setText("Rezervacija za " + x.brojOsoba + " osoba.");
                return view;
            }
        };
        lvRezervacije.setAdapter(adapter);
        lvRezervacije.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                RezervacijaResultVM.Row x = podaci.rows.get(position);
                do_listViewLongClick(x);
                return true;
            }
        });
    }

    private void do_listViewLongClick(final RezervacijaResultVM.Row x) {
        final AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        adb.setMessage("Da li ste sigurni da želite obrisati rezervaciju?");
        adb.setPositiveButton("DA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Todo ukloniti rezervaciju iz baze
                //Storage.RemoveRezervacija(x);
                ukloniRezervaciju(x.id);
                dialog.dismiss();
                adapter.notifyDataSetChanged();
            }
        });


        adb.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        adb.setIcon(android.R.drawable.ic_dialog_alert);
        adb.show();
    }

    @Override
    public void onResume() {
        popuniPodatkeTask();
        super.onResume();
    }

    private void ukloniRezervaciju(final int id) {
        new AsyncTask<Void, Void, RezervacijaDeleteVM>() {
            private ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                progressDialog = ProgressDialog.show(getActivity(), "Loading", "Sačekajte...");
            }


            @Override
            protected RezervacijaDeleteVM doInBackground(Void... voids) {
                RezervacijaDeleteVM deleteVM = new RezervacijaDeleteVM(id);

                String strGson=deleteVM!=null? MyGson.build().toJson(deleteVM):" ";

                String strJson = MyUrlConnection.Post(MyConfig.baseUrl + "api/Rezervacija/delete",strGson);
                RezervacijaDeleteVM x = MyGson.build().fromJson(strJson, RezervacijaDeleteVM.class);
                return x;
            }

            @Override
            protected void onPostExecute(RezervacijaDeleteVM x) {


                if (x.Id == 0){
                    popuniPodatkeTask();
                    Toast.makeText(getActivity(),"Rezervacija uklonjena!", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getActivity(),"Nesto je poslo po zlu!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();

            }
        }.execute();
    }


}
