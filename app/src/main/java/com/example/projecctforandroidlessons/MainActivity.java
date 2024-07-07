package com.example.projecctforandroidlessons;

import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private CreateAccountFragment createAccountFragment = new CreateAccountFragment();
    private HomeFragment homeFragment = new HomeFragment();

    private FragmentSettings fragmentSettings = new FragmentSettings();

    private ProfileFragment profileFragment = new ProfileFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private TicketsFragment ticketsFragment = new TicketsFragment();

    private DestinationFragment destinationFragment = new DestinationFragment();

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    private void init(){

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.tool_bar_id);
    }


    private void setThemeBasedOnPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean useDarkTheme = sharedPreferences.getBoolean("theme_pref", false);
        if (useDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeBasedOnPreferences();
        setContentView(R.layout.activity_main);
        init();

        setupNavigationDrawer();

        setupInitialFragment(savedInstanceState);

    }

    private void setupNavigationDrawer() {
        setSupportActionBar( toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupInitialFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            // Show HomeFragment on initial launch
            replaceFragment(homeFragment);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setThemeBasedOnPreferences();
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
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.second_fragment_container, fragment)
                .commit();
    }

    private void addFragment(Fragment fragment){

        // Проверяем, добавлен ли фрагмент уже
        Fragment existingFragment = getSupportFragmentManager().findFragmentById(R.id.first_fragment_container);
        if (existingFragment != null && existingFragment.getClass().equals(fragment.getClass())) {
            // Фрагмент уже добавлен, не делаем ничего
            return;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.first_fragment_container, fragment)
                .addToBackStack(null)
                .commit();

    }

    private void removeFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .remove(fragment)
                .commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if(itemId == R.id.home_id){
            replaceFragment(homeFragment);
            Toast.makeText(this, "menu_profile_id", Toast.LENGTH_LONG).show();
        }
        if(itemId == R.id.settings_id){
            replaceFragment(fragmentSettings);
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
        if(itemId == R.id.search_id){
            addFragment(searchFragment);
            replaceFragment(ticketsFragment);
        }
        if(itemId == R.id.destination_id){
            addFragment(searchFragment);
            replaceFragment(destinationFragment);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}