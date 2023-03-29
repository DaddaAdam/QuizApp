package com.emsi.QuizApp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.Map;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        TextView questionNumber = (TextView) findViewById(R.id.questionNumber);
        ImageView quiz_image = (ImageView) findViewById(R.id.quiz_image);
        TextView questionContent = (TextView) findViewById(R.id.questionContent);

        Button answer1 = (Button) findViewById(R.id.answer_1);
        Button answer2 = (Button) findViewById(R.id.answer_2);
        Button answer3 = (Button) findViewById(R.id.answer_3);
        Button answer4 = (Button) findViewById(R.id.answer_4);

        int questionCount=1;
        DocumentReference docRef = db.collection("Questions").document("q1");

        docRef
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Map<String, Object> question = documentSnapshot.getData();

                        if(question != null){

                        questionNumber.setText((String)"Question N°"+questionCount);
                        questionContent.setText((String)question.get("content"));
                        ArrayList<String> answers =  (ArrayList<String>) question.get("answers");
                        answer1.setText(answers.get(0));
                        answer2.setText(answers.get(1));
                        answer3.setText(answers.get(2));
                        answer4.setText(answers.get(3));

                        String imageUrl = (String)question.get("imageUrl");
                        Picasso.get().load(imageUrl).into(quiz_image);
                    }
                    }
                });
    }

}