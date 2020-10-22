package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void logout(View view) {
        //setContentView(R.layout.activity_main);
        Intent redirect = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(redirect);
    }
}