package com.example.eventosapp.eventosapp.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.eventosapp.eventosapp.Fragments.CategoriaFragment;
import com.example.eventosapp.eventosapp.Fragments.EventosMapFragment;
import com.example.eventosapp.eventosapp.Fragments.LoginFragment;
import com.example.eventosapp.eventosapp.Fragments.PerfilFragment;
import com.example.eventosapp.eventosapp.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {

    LoginFragment loginFragment;
    CategoriaFragment categoriaFragment;
    EventosMapFragment eventosMapFragment;
    public static final String HOST = "http://192.168.8.102";
    public static final String PATH = "/images/";
    private boolean CheckLogin = false;

    private BottomNavigationViewEx.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationViewEx.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    CheckSesion();
                    return true;
                case R.id.navigation_dashboard:
                    categoriaFragment = new CategoriaFragment();
                    setFragment(categoriaFragment);
                    return true;
                case R.id.navigation_notifications:
                    eventosMapFragment = new EventosMapFragment();
                    setFragment(eventosMapFragment);
                    return true;
            }
            return false;
        }
    };

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
//        fragmentTransaction.disallowAddToBackStack();
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationViewEx navigation = (BottomNavigationViewEx) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.enableAnimation(false);
        navigation.enableShiftingMode(false);
        navigation.enableItemShiftingMode(false);
//        loginFragment = new LoginFragment();
//        setFragment(loginFragment);
        CheckSesion();
    }

    public void CheckSesion() {
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("MiSesion", Context.MODE_PRIVATE);
        CheckLogin = sharedPreferences.getBoolean("LoginOn", false);
        if (CheckLogin) {
            PerfilFragment perfilFragment = new PerfilFragment();
            setFragment(perfilFragment);
        } else {
            LoginFragment loginFragment = new LoginFragment();
            setFragment(loginFragment);
        }
    }


}
