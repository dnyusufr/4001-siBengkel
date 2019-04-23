package com.example.sibengkel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //declaration
    TextView txt_profile_name;
    Button btn_logout;

    SharedPreferences sharedpreferences;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        //setnama
        txt_profile_name = (TextView) findViewById(R.id.txt_profile_name);
        txt_profile_name.setText(getIntent().getExtras().get("name").toString());

        //btnforlogout
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

//        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
//        bottomNav.setOnNavigationItemSelectedListener(navListener);
//
//        if(savedInstanceState==null){
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
//        }
//    }
//
//    private BottomNavigationView.OnNavigationItemSelectedListener navListener =  new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//            Fragment selectedFragment = null;
//
//            switch (menuItem.getItemId()) {
//                case R.id.nav_home:
//                    selectedFragment = new HomeFragment();
//                    break;
//                case R.id.nav_profile:
//                    selectedFragment = new SettingFragment();
//                    break;
//            }
//
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    selectedFragment).commit();
//
//            return true;
//        }
    };

    public void bookNow(View view){
        intent = new Intent(MainActivity.this, ScheduleActivity.class);
        intent.putExtra("email", getIntent().getExtras().get("email").toString());
        startActivity(intent);
    }

    private void logout() {
        sharedpreferences = getSharedPreferences(
                LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(LoginActivity.session_status, false);
        editor.putString(LoginActivity.TAG_ID, null);
        editor.putString(LoginActivity.TAG_EMAIL, null);
        editor.putString(LoginActivity.TAG_NAME, null);
        editor.putString(LoginActivity.TAG_PHONE, null);
        editor.putString(LoginActivity.TAG_ADDRESS, null);
        editor.commit();

        intent = new Intent(MainActivity.this, LoginActivity.class);
        finish();
        startActivity(intent);
    }
}
