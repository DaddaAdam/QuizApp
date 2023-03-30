package com.emsi.QuizApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class PickDifficultyActivity extends AppCompatActivity {

    Button easyBtn, intermediateBtn, hardBtn;
    List<Button> btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_difficulty);

        easyBtn = (Button) findViewById(R.id.easy_btn);
        intermediateBtn = (Button) findViewById(R.id.intermediate_btn);
        hardBtn = (Button) findViewById(R.id.difficult_btn);

        btnList = new ArrayList<Button>();
        btnList.add(easyBtn);
        btnList.add(intermediateBtn);
        btnList.add(hardBtn);

        for(Button btn: btnList){
            btn.setOnClickListener(v -> {
                Intent intent = new Intent(PickDifficultyActivity.this, QuizActivity.class);
                intent.putExtra("difficulty", btnList.indexOf(btn) + 1);
                startActivity(intent);
            });
        }
    }
}