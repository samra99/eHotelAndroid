package com.example.hotelapplication.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hotelapplication.R;
import com.example.hotelapplication.helper.MyConfig;
import com.example.hotelapplication.helper.MyFragmentUtils;
import com.example.hotelapplication.helper.MyGson;
import com.example.hotelapplication.helper.MyRunnable;
import com.example.hotelapplication.helper.MyUrlConnection;
import com.example.hotelapplication.model.Soba;
import com.example.hotelapplication.model.SobePregledVM;
import com.example.hotelapplication.model.Storage;

import java.util.List;


public class SobeListFragment extends Fragment {


    private ListView lvSobe;
    SobePregledVM.Row soba = new SobePregledVM.Row();

    public static SobeListFragment newInstance() {
        SobeListFragment fragment = new SobeListFragment();
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
        View view = inflater.inflate(R.layout.fragment_sobe_list, container, false);
        lvSobe = view.findViewById(R.id.lvSobe);
        popuniPodatkeTask();
        return view;
    }

    private void popuniPodatkeTask() {

        new AsyncTask<Void, Void, SobePregledVM>() {
            private ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                progressDialog = ProgressDialog.show(getActivity(), "Loading", "Sačekajte...");
            }


            @Override
            protected SobePregledVM doInBackground(Void... voids) {

                String strJson = MyUrlConnection.Get(MyConfig.baseUrl + "Soba/Index");
                SobePregledVM x = MyGson.build().fromJson(strJson, SobePregledVM.class);
                return x;
            }

            @Override
            protected void onPostExecute(SobePregledVM x) {

                progressDialog.dismiss();
                popuniPodatke(x);
            }
        }.execute();
    }


    private void popuniPodatke(final SobePregledVM podaci) {

        //final List<Soba> podaci=Storage.getSobe();
        lvSobe.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return podaci.rows.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup parent) {
                if (view == null) {
                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.sobe_stavka, parent, false);
                }
                ImageView img = view.findViewById(R.id.img);
                TextView txtFirstLine = view.findViewById(R.id.txtFirstLine);
                TextView txtSecindLine = view.findViewById(R.id.txtSecondLine);


                SobePregledVM.Row x = podaci.rows.get(i);
                img.setImageResource(Storage.getSobe().get(i).getImageID());
                txtFirstLine.setText(x.naziv);
                txtSecindLine.setText(x.opis);


                return view;
            }
        });

        lvSobe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                soba = podaci.rows.get(position);

                MyFragmentUtils.openAsReplace(getActivity(), R.id.mjestoFragment, SobeDetaljiFragment.newInstance(soba));
            }
        });

    }


}


