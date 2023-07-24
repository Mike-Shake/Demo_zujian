package com.example.navigation_buttonnavigation;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottom_nav_view = findViewById(R.id.bottom_nav_view);

        NavHostFragment navHostFragment =(NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(bottom_nav_view,navController);

    }
}