package com.emsi.QuizApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuizActivity extends AppCompatActivity {
    //Global variables
    private static int questionCount=0;
    private static int score = 0;

    //UI elements
    Button answer1, answer2, answer3, answer4;
    TextView questionNumber, questionContent;
    ImageView quiz_image;

    //Firebase elements
    FirebaseFirestore db;
    List<DocumentSnapshot> documents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        db = FirebaseFirestore.getInstance();

        questionNumber = (TextView) findViewById(R.id.questionNumber);
        quiz_image = (ImageView) findViewById(R.id.quiz_image);
        questionContent = (TextView) findViewById(R.id.questionContent);

        answer1 = (Button) findViewById(R.id.answer_1);
        answer2 = (Button) findViewById(R.id.answer_2);
        answer3 = (Button) findViewById(R.id.answer_3);
        answer4 = (Button) findViewById(R.id.answer_4);

        db.collection("Questions")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            documents = task.getResult().getDocuments();
                            DisplayQuestion(documents.get(questionCount).getData());
                        }
                    }
                });

        answer1.setOnClickListener(v -> {
            ProcessQuestions(answer1.getText().toString());
        });
        answer2.setOnClickListener(v -> {
            ProcessQuestions(answer2.getText().toString());
        });
        answer3.setOnClickListener(v -> {
            ProcessQuestions(answer3.getText().toString());
        });
        answer4.setOnClickListener(v -> {
            ProcessQuestions(answer4.getText().toString());
        });
    }

    public void DisplayQuestion(Map<String, Object> question){
        ArrayList<String> answers =  (ArrayList<String>) question.get("answers");

        questionNumber.setText((String)"Question N°"+questionCount+1);
        questionContent.setText((String)question.get("content"));
        
        answer1.setText(answers.get(0));
        answer2.setText(answers.get(1));
        answer3.setText(answers.get(2));
        answer4.setText(answers.get(3));

        String imageUrl = (String)question.get("imageUrl");
        Picasso.get().load(imageUrl).into(quiz_image);
    }

    public void ProcessQuestions(String answer){

        if(answer.equals(documents.get(questionCount).getData().get("correct_answer")))
            score++;

        if(questionCount < documents.size())
            DisplayQuestion(documents.get(++questionCount).getData());

        else
            Toast.makeText(QuizActivity.this, "No more questions.",
                    Toast.LENGTH_SHORT).show();

    }

}