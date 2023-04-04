package com.emsi.QuizApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    Button playAgain;
    TextView scoreText;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        int questionCount = intent.getIntExtra("questionCount", 10);

        playAgain = (Button) findViewById(R.id.play_again);
        scoreText = (TextView) findViewById(R.id.score);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        scoreText.setText( score + "/" + questionCount);
        int progress = (score * 100) /questionCount;
        progressBar.setProgress(progress);



        playAgain.setOnClickListener(v -> {
            Intent newIntent = new Intent(SummaryActivity.this, PickDifficultyActivity.class);
            startActivity(newIntent);
        });
    }
}