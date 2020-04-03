package com.morashstudios.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dl = findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.contentFragment, new MainScreenFragment()).commit();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        NavigationView nv = findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                switch(id)
                {

                    case R.id.main_screen:
                        transaction.replace (R.id.contentFragment, new MainScreenFragment()).commit();
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.bodyfat_calc:
                        transaction.replace(R.id.contentFragment, new BodyfatCalculatorFragment()).commit();
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.bmi_calc:
                        transaction.replace(R.id.contentFragment, new BodyIndexFragment()).commit();
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.calorie_calc:
                        transaction.replace(R.id.contentFragment, new CalorieFragment()).commit();
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.macro_calc:
                        transaction.replace(R.id.contentFragment, new MacroFragment()).commit();
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.repmax_calc:
                        transaction.replace(R.id.contentFragment, new OneRepMaxFragment()).commit();
                        dl.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.settings:
                        Intent settingsIntent = new Intent (MainActivity.this, SettingsActivity.class);
                        startActivity(settingsIntent);
                    default:
                        return true;
                }


                return true;

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
