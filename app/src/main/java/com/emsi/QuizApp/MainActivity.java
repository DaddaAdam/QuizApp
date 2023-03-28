package com.emsi.QuizApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView email = (TextView) findViewById(R.id.email);
        TextView password = (TextView) findViewById(R.id.password);

        Button loginBtn = (Button) findViewById(R.id.loginButton);

        loginBtn.setOnClickListener(v -> {
            if(!email.getText().toString().isEmpty()
                    && !password.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "LOGIN SUCCESSFUL!", Toast.LENGTH_LONG).show();
            }
        });

    }
}