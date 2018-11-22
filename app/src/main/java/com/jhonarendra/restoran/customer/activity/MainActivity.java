package com.jhonarendra.restoran.customer.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.jhonarendra.restoran.customer.R;
import com.jhonarendra.restoran.customer.fragment.CommentFragment;
import com.jhonarendra.restoran.customer.fragment.HomeFragment;
import com.jhonarendra.restoran.customer.fragment.UserFragment;

public class MainActivity extends AppCompatActivity {
    public static final String URL = "http://192.168.43.102:8000/";
//    public static final String URL = "http://jonarendra.000webhostapp.com/api/progmob15/";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_user:
                    selectedFragment = new UserFragment();
                    break;
                case R.id.navigation_comment:
                    selectedFragment = new CommentFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

}
