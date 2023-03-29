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

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        TextView email = (TextView) findViewById(R.id.email);
        TextView password = (TextView) findViewById(R.id.password);
        TextView passwordConfirm = (TextView) findViewById(R.id.password_confirm);

        Button loginBtn = (Button) findViewById(R.id.loginButton);
        TextView toSignUp = (TextView) findViewById(R.id.toRegister);

        toSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        });

        loginBtn.setOnClickListener(v -> {
            if(!email.getText().toString().isEmpty()
                    && !password.getText().toString().isEmpty()
                        && !passwordConfirm.getText().toString().isEmpty()) {

                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();
                String passwordConfirmString = passwordConfirm.getText().toString();

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
            }
        });

    }
}