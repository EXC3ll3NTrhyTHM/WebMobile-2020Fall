package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final String username = usernameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();

        if (username.equals("Admin") && password.equals("Admin")) {
            Intent redirect = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(redirect);
        }
        if (!username.equals("Admin")) {
            usernameEditText.setError("Incorrect");
        }
        if (!password.equals("Admin")) {
            passwordEditText.setError("Incorrect");
        }
    }
}