package com.example.eventosapp.eventosapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.eventosapp.eventosapp.Fragments.DeporteFragment;
import com.example.eventosapp.eventosapp.Fragments.DestacadoFragment;
import com.example.eventosapp.eventosapp.Fragments.EventosFragment;
import com.example.eventosapp.eventosapp.Fragments.FiestaFragment;
import com.example.eventosapp.eventosapp.Fragments.GastronomiaFragment;
import com.example.eventosapp.eventosapp.Fragments.MusicaFragment;
import com.example.eventosapp.eventosapp.Fragments.OtrosFragment;

import java.util.ArrayList;
import java.util.List;

public class CategoriaPageAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    EventosFragment eventosFragment;
    FiestaFragment fiestaFragment;
    MusicaFragment musicaFragment;
    GastronomiaFragment gastronomiaFragment;
    DeporteFragment deporteFragment;
    OtrosFragment otrosFragment;
    public CategoriaPageAdapter(FragmentManager fm,int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
//                DestacadoFragment destacadoFragment = new DestacadoFragment();
                eventosFragment = new EventosFragment();
                return eventosFragment;
            case 1:
                fiestaFragment  = new FiestaFragment();
                return fiestaFragment;
            case 2:
                musicaFragment = new MusicaFragment();
                return musicaFragment;
            case 3:
                gastronomiaFragment = new GastronomiaFragment();
                return gastronomiaFragment;
            case 4:
                deporteFragment = new DeporteFragment();
                return deporteFragment;
            case 5:
                otrosFragment = new OtrosFragment();
                return otrosFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
