package com.example.eventosapp.eventosapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventosapp.eventosapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilPremiumFragment extends Fragment {


    public PerfilPremiumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil_premium, container, false);
    }

}
