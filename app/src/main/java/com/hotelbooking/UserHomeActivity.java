package com.hotelbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hotelbooking.fragment.HomeFragment;
import com.hotelbooking.fragment.MyBookingsFragment;
import com.hotelbooking.fragment.ProfileFragment;
import com.hotelbooking.fragment.SearchFragment;

public class UserHomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        bottomNavigation();
    }


    private void bottomNavigation() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        bottomNavigationView.setSelectedItemId(R.id.action_item1);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = HomeFragment.homeFragment();
                                break;
                            case R.id.action_item2:
                                selectedFragment = SearchFragment.searchFragment();

                                break;
                            case R.id.action_item3:
                                selectedFragment = ProfileFragment.profileFragment();

                                break;
                            case R.id.action_bookings:
                                selectedFragment = MyBookingsFragment.myBookingsFragment();
                                break;
                            case R.id.action_logout:
                                Intent i= new Intent(UserHomeActivity.this, LoginActivity.class);
                                startActivity(i);
                                break;

                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment.homeFragment());
        transaction.commit();
    }
}