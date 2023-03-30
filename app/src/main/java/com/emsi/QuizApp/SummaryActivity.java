package com.emsi.QuizApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    Button playAgain;
    TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        int questionCount = intent.getIntExtra("questionCount", 10);

        playAgain = (Button) findViewById(R.id.play_again);
        scoreText = (TextView) findViewById(R.id.score);

        scoreText.setText( score + "/" + questionCount);

        playAgain.setOnClickListener(v -> {
            Intent newIntent = new Intent(SummaryActivity.this, QuizActivity.class);
            startActivity(newIntent);
        });
    }
}