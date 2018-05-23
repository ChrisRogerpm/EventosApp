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
public class MusicaFragment extends Fragment {


    public MusicaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_musica, container, false);
        return v;
    }

}
