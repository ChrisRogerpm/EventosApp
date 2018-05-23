package com.example.eventosapp.eventosapp.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventosapp.eventosapp.Adapters.CategoriaPageAdapter;
import com.example.eventosapp.eventosapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriaFragment extends Fragment {

    private CategoriaPageAdapter adapter;
    private ViewPager viewPager;

    public CategoriaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_categoria, container, false);

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Destacado"));
        tabLayout.addTab(tabLayout.newTab().setText("Fiestas"));
        tabLayout.addTab(tabLayout.newTab().setText("Música"));
        tabLayout.addTab(tabLayout.newTab().setText("Gastronomía"));
        tabLayout.addTab(tabLayout.newTab().setText("Deporte"));
        tabLayout.addTab(tabLayout.newTab().setText("Otro"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) v.findViewById(R.id.container_viewpager);
        adapter = new CategoriaPageAdapter(getFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return v;
    }


}
