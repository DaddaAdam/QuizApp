package com.emsi.QuizApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
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
    ArrayList<Button> buttonList;
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

        //Bind the UI elements to the java code
        questionNumber = (TextView) findViewById(R.id.questionNumber);
        quiz_image = (ImageView) findViewById(R.id.quiz_image);
        questionContent = (TextView) findViewById(R.id.questionContent);

        answer1 = (Button) findViewById(R.id.answer_1);
        answer2 = (Button) findViewById(R.id.answer_2);
        answer3 = (Button) findViewById(R.id.answer_3);
        answer4 = (Button) findViewById(R.id.answer_4);

        buttonList = new ArrayList<Button>();
        buttonList.add(answer1);
        buttonList.add(answer2);
        buttonList.add(answer3);
        buttonList.add(answer4);


        //Query the database, get all the documents and parse them
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

        //Add event listener to each button, then use the text to check wether the answer is correct
        for(Button btn: buttonList){
            btn.setOnClickListener(v -> {
                ProcessQuestions(btn.getText().toString());
            });
        }
    }

    public void DisplayQuestion(Map<String, Object> question){
        //Parse the array of possible answers from the json
        ArrayList<String> answers =  (ArrayList<String>) question.get("answers");

        //Assign each field from the object to the UI elements
        questionNumber.setText((String)"Question N°"+ (int)(questionCount+1));
        questionContent.setText((String)question.get("content"));

        for(int i=0; i < buttonList.size(); i++){
            if(i>answers.size() - 1)
                buttonList.get(i).setVisibility(View.GONE);

            else{
                buttonList.get(i).setVisibility(View.VISIBLE);
                buttonList.get(i).setText(answers.get(i));
            }
        }

        //Using picasso, load the image and add it to the ImageView component
        String imageUrl = (String)question.get("imageUrl");
        Picasso.get().load(imageUrl).into(quiz_image);
    }

    public void ProcessQuestions(String answer){
        //If the answer is correct, increment score
        if(answer.equals(documents.get(questionCount).getData().get("correct_answer")))
            score++;

        //If the quiz is not over yet, go to next question
        if(questionCount < documents.size()-1)
            DisplayQuestion(documents.get(++questionCount).getData());

        //Else, go to summary activity and display final score
        else{
            Intent intent = new Intent(QuizActivity.this, SummaryActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("questionCount", ++questionCount);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        score=0;
        questionCount=0;
    }
}