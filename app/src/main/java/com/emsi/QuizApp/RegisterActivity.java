package com.emsi.QuizApp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        //Get the Firebase instance
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        //Bind all the UI elements to the Java code
        TextView email = (TextView) findViewById(R.id.email);
        TextView password = (TextView) findViewById(R.id.password);
        TextView passwordConfirm = (TextView) findViewById(R.id.password_confirm);

        Button loginBtn = (Button) findViewById(R.id.loginButton);
        TextView toSignUp = (TextView) findViewById(R.id.toRegister);

        //If you already have an account, redirect to Sign In activity
        toSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        });

        loginBtn.setOnClickListener(v -> {
            //Making sure all the informations have been filled
            if(!email.getText().toString().isEmpty()
                    && !password.getText().toString().isEmpty()
                        && !passwordConfirm.getText().toString().isEmpty()) {

                //Retrieving the informations from the TextViews
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();
                String passwordConfirmString = passwordConfirm.getText().toString();

                //Making sure both passwords match
                if (!passwordString.equals(passwordConfirmString)) {
                    Toast.makeText(RegisterActivity.this, "Please make sure both password match",
                            Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(emailString, passwordString)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(RegisterActivity.this, "Registration succeeded.",
                                                Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(RegisterActivity.this, "Registration failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
            }
            } else {
                Toast.makeText(RegisterActivity.this, "Please make sure to fill all the informations.",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}