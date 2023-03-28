package com.emsi.QuizApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        setContentView(R.layout.activity_main);

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        TextView email = (TextView) findViewById(R.id.email);
        TextView password = (TextView) findViewById(R.id.password);

        Button loginBtn = (Button) findViewById(R.id.loginButton);

        TextView toSignUp = (TextView) findViewById(R.id.toRegister);

        toSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        loginBtn.setOnClickListener(v -> {
            if(!email.getText().toString().isEmpty()
                    && !password.getText().toString().isEmpty()) {

                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();

                mAuth.signInWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(MainActivity.this, "Authentication succeeded.",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });

    }
}