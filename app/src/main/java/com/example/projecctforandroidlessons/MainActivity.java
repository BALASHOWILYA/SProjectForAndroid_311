package com.example.projecctforandroidlessons;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private CreateAccountFragment createAccountFragment = new CreateAccountFragment();
    private HomeFragment homeFragment = new HomeFragment();

    private ProfileFragment profileFragment = new ProfileFragment();

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    private void init(){

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.tool_bar_id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();


        setSupportActionBar( toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        FragmentManager fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            // Показать HomeFragment при запуске
            replaceFragment(homeFragment);
        }

        navigationView.setNavigationItemSelectedListener(this);






    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private void addFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    private void removeFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if(itemId == R.id.home_id){
            replaceFragment(homeFragment);
            Toast.makeText(this, "menu_profile_id", Toast.LENGTH_LONG).show();
        }
        if(itemId == R.id.settings_id){
            Toast.makeText(this, "menu_exit_id", Toast.LENGTH_LONG).show();
        }
        if(itemId == R.id.nav_profile){
            replaceFragment(profileFragment);
            Toast.makeText(this, "menu_settings_id", Toast.LENGTH_LONG).show();
        }
        if(itemId == R.id.nav_login){
            replaceFragment(createAccountFragment);
            Toast.makeText(this, "menu_settings_id", Toast.LENGTH_LONG).show();
        }
        if(itemId == R.id.nav_logout){
            Toast.makeText(this, "menu_settings_id", Toast.LENGTH_LONG).show();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }





}