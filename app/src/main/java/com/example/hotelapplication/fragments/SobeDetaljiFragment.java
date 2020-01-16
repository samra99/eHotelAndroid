package com.example.hotelapplication.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hotelapplication.R;
import com.example.hotelapplication.RezervacijaDodaj;
import com.example.hotelapplication.helper.MyFragmentUtils;
import com.example.hotelapplication.model.SobePregledVM;

import java.io.Serializable;


public class SobeDetaljiFragment extends Fragment {

    private static final String SOBA_KEY = "soba_key";
    private SobePregledVM.Row soba;

    FloatingActionButton btnReserve;
    private TextView txtTitle;
    private TextView txtDescription;



    public SobeDetaljiFragment() {
        // Required empty public constructor
    }


    public static SobeDetaljiFragment newInstance(SobePregledVM.Row _soba) {
        SobeDetaljiFragment fragment = new SobeDetaljiFragment();
        Bundle args = new Bundle();
        //TODO nesto nije bilo uredu
        args.putSerializable(SOBA_KEY, (Serializable) _soba);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           soba= (SobePregledVM.Row) getArguments().getSerializable(SOBA_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        View view= inflater.inflate(R.layout.fragment_sobe_detalji, container, false);
       // textView.setText(R.string.hello_blank_fragment);
        txtTitle=view.findViewById(R.id.resTitle);
        txtDescription=view.findViewById(R.id.resDescription);

        txtTitle.setText(soba.naziv);
        txtDescription.setText(soba.detalji);

        btnReserve=view.findViewById(R.id.btnIdiNaRezervacije);
        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // MyFragmentUtils.openAsReplace(getActivity(),R.id.mjestoFragment,RezervacijaDodajFragment.newInstance(soba));
                Intent i = new Intent(getActivity(), RezervacijaDodaj.class);
                startActivity(i);

            }
        });
        return view;
    }


}
