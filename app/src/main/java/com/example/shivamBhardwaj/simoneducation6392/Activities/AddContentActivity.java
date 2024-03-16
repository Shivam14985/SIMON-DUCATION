package com.example.shivamBhardwaj.simoneducation6392.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.shivamBhardwaj.simoneducation6392.Models.PracticeQuestionModel;
import com.example.shivamBhardwaj.simoneducation6392.Models.QuizModel;
import com.example.shivamBhardwaj.simoneducation6392.R;
import com.example.shivamBhardwaj.simoneducation6392.databinding.ActivityAddContentBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Date;
import java.util.UUID;

public class AddContentActivity extends AppCompatActivity {
    ActivityAddContentBinding binding;
    FirebaseDatabase database;
    FirebaseStorage storage;
    ArrayAdapter<String> adapter;
    String[] items = {"General Knowledge", "Mathematics", "Science", "History", "Geographical", "English", "Geography", "Economics", "Facts", "Computer"};
    String[] QuizSubject = {"General Knowledge", "Mathematics", "Science", "History", "Geographical", "English", "Geography", "Economics", "Computer"};
    String[] language = {"English", "Hindi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAddContentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        getSupportActionBar().hide();


        adapter = new ArrayAdapter<String>(AddContentActivity.this, R.layout.autocompletelistenor, QuizSubject);
        binding.EtQuizSubject.setAdapter(adapter);

        adapter = new ArrayAdapter<String>(AddContentActivity.this, R.layout.autocompletelistenor, language);
        binding.EtQuizLanguage.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(AddContentActivity.this, R.layout.autocompletelistenor, items);
        binding.EtSubject.setAdapter(adapter);

        adapter = new ArrayAdapter<String>(AddContentActivity.this, R.layout.autocompletelistenor, language);
        binding.EtLanguage.setAdapter(adapter);

        binding.tvaddknoledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change the background color temporarily
                int originalColor = Color.parseColor("#FFFFFFFF");
                int clickedColor = Color.parseColor("#E8E8E8"); // Replace with your desired color

                // Change background color when clicked
                binding.tvaddknoledge.setBackgroundColor(clickedColor);

                // Set a delayed runnable to revert the color after a short duration (e.g., 500 milliseconds)
                binding.tvaddknoledge.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.tvaddknoledge.setBackgroundColor(originalColor);
                    }
                }, 100);
                binding.addknowledgeLayout.setVisibility(View.VISIBLE);
            }
        });
        binding.btnAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(AddContentActivity.this, R.anim.bounce);
                final Animation animation1 = AnimationUtils.loadAnimation(AddContentActivity.this, R.anim.bonceexit);
                binding.btnAddQuestion.startAnimation(animation);
                binding.btnAddQuestion.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        binding.btnAddQuestion.startAnimation(animation1);
                    }
                }, 0);
                String Subject = binding.EtQuizSubject.getText().toString();
                String Language = binding.EtQuizLanguage.getText().toString();
                String quizNumber = binding.SpecificChild.getText().toString();
                String quizid = UUID.randomUUID().toString();

                String Question1 = binding.Question1.getText().toString();
                String q1Option1 = binding.Option1.getText().toString();
                String q1Option2 = binding.Option2.getText().toString();
                String q1Option3 = binding.Option3.getText().toString();
                String q1Option4 = binding.Option4.getText().toString();
                String q1Correct = binding.correctanswe1.getText().toString();

                String Question2 = binding.Question2.getText().toString();
                String q2Option1 = binding.q2Option1.getText().toString();
                String q2Option2 = binding.q2Option2.getText().toString();
                String q2Option3 = binding.q2Option3.getText().toString();
                String q2Option4 = binding.q2Option4.getText().toString();
                String q2Correct = binding.q2correctanswe.getText().toString();

                String Question3 = binding.Question3.getText().toString();
                String q3Option1 = binding.q3Option1.getText().toString();
                String q3Option2 = binding.q3Option2.getText().toString();
                String q3Option3 = binding.q3Option3.getText().toString();
                String q3Option4 = binding.q3Option4.getText().toString();
                String q3Correct = binding.q3correctanswe.getText().toString();

                String Question4 = binding.Question4.getText().toString();
                String q4Option1 = binding.q4Option1.getText().toString();
                String q4Option2 = binding.q4Option2.getText().toString();
                String q4Option3 = binding.q4Option3.getText().toString();
                String q4Option4 = binding.q4Option4.getText().toString();
                String q4Correct = binding.q4correctanswe.getText().toString();

                String Question5 = binding.Question5.getText().toString();
                String q5Option1 = binding.q5Option1.getText().toString();
                String q5Option2 = binding.q5Option2.getText().toString();
                String q5Option3 = binding.q5Option3.getText().toString();
                String q5Option4 = binding.q5Option4.getText().toString();
                String q5Correct = binding.q5correctanswe1.getText().toString();

                QuizModel model = new QuizModel();
                model.setQuizSubject(Subject);
                model.setQuizLanguage(Language);
                model.setQuizAddedAt(new Date().getTime());
                model.setQuizNumber(quizNumber);
                model.setQuizId(quizid);

                model.setQuestion1(Question1);
                model.setQ1Option1(q1Option1);
                model.setQ1Option2(q1Option2);
                model.setQ1Option3(q1Option3);
                model.setQ1Option4(q1Option4);
                model.setQ1correctAnswer(q1Correct);

                model.setQuestion2(Question2);
                model.setQ2Option1(q2Option1);
                model.setQ2Option2(q2Option2);
                model.setQ2Option3(q2Option3);
                model.setQ2Option4(q2Option4);
                model.setQ2correctAnswer(q2Correct);

                model.setQuestion3(Question3);
                model.setQ3Option1(q3Option1);
                model.setQ3Option2(q3Option2);
                model.setQ3Option3(q3Option3);
                model.setQ3Option4(q3Option4);
                model.setQ3correctAnswer(q3Correct);

                model.setQuestion4(Question4);
                model.setQ4Option1(q4Option1);
                model.setQ4Option2(q4Option2);
                model.setQ4Option3(q4Option3);
                model.setQ4Option4(q4Option4);
                model.setQ4correctAnswer(q4Correct);

                model.setQuestion5(Question5);
                model.setQ5Option(q5Option1);
                model.setQ5Option2(q5Option2);
                model.setQ5Option3(q5Option3);
                model.setQ5Option4(q5Option4);
                model.setQ5correctAnswer(q5Correct);

                database.getReference().child("Quiz").child(Language).child(quizid).setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddContentActivity.this, "Quiz Added", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(AddContentActivity.this, R.anim.bounce);
                final Animation animation1 = AnimationUtils.loadAnimation(AddContentActivity.this, R.anim.bonceexit);
                binding.button.startAnimation(animation);
                binding.button.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        binding.button.startAnimation(animation1);
                    }
                }, 0);
                if (binding.editTextText.getText().toString().isEmpty()) {
                    Toast.makeText(AddContentActivity.this, "Fill", Toast.LENGTH_SHORT).show();
                } else {
                    PracticeQuestionModel model = new PracticeQuestionModel();
                    model.setDescriptionSubject(binding.EtSubject.getText().toString());
                    model.setAddedAt(new Date().getTime());
                    model.setAddedBy(FirebaseAuth.getInstance().getUid());
                    model.setLanguage(binding.EtLanguage.getText().toString());
                    model.setDescription(binding.editTextText.getText().toString());

//                    database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("contribution").addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            Integer i = Integer.parseInt(snapshot.getValue().toString());
//                            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("contribution").setValue(i+10);
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
                    database.getReference().child("Learning Question").child(binding.EtLanguage.getText().toString()).push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(AddContentActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(AddContentActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}