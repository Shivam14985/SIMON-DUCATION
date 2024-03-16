package com.example.shivamBhardwaj.simoneducation6392.Activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.shivamBhardwaj.simoneducation6392.Models.QuizModel;
import com.example.shivamBhardwaj.simoneducation6392.R;
import com.example.shivamBhardwaj.simoneducation6392.databinding.ActivityGiveQuizBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class GiveQuizActivity extends AppCompatActivity {
    ActivityGiveQuizBinding binding;
    QuizModel Quiz = null;
    public int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityGiveQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                }
        });

        new CountDownTimer(50000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                binding.CountDown.setText(String.valueOf(counter));
                counter++;
            }
            @Override
            public void onFinish() {
                binding.CountDown.setText("Finished");
            }
        }.start();
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/simon-education.appspot.com/o/App%20Photos%2Ftimer.png?alt=media&token=2e1df5c8-0ce9-4439-a8b5-7ec03d459cfc").placeholder(R.drawable.gallery).into(binding.HightimeimageView);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/simon-education.appspot.com/o/App%20Photos%2Ftimer.png?alt=media&token=2e1df5c8-0ce9-4439-a8b5-7ec03d459cfc").placeholder(R.drawable.gallery).into(binding.clockimage);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/simon-education.appspot.com/o/App%20Photos%2Fhigh-score.png?alt=media&token=741969cd-813b-4ed8-878d-c35ff4cd4e54").placeholder(R.drawable.gallery).into(binding.HighimageView);

        final Object QuizObject = getIntent().getSerializableExtra("Quiz");
        if (QuizObject instanceof QuizModel) {
            Quiz = (QuizModel) QuizObject;
        }
        if (Quiz != null) {
            binding.tooltext.setText(Quiz.getQuizNumber());

            binding.Question1.setText(Quiz.getQuestion1());
            binding.Option1.setText(Quiz.getQ1Option1());
            binding.Option2.setText(Quiz.getQ1Option2());
            binding.Option3.setText(Quiz.getQ1Option3());
            binding.Option4.setText(Quiz.getQ1Option4());

            binding.Question2.setText(Quiz.getQuestion2());
            binding.q2Option1.setText(Quiz.getQ2Option1());
            binding.q2Option2.setText(Quiz.getQ2Option2());
            binding.q2Option3.setText(Quiz.getQ2Option3());
            binding.q2Option4.setText(Quiz.getQ2Option4());

            binding.Question3.setText(Quiz.getQuestion3());
            binding.q3Option1.setText(Quiz.getQ3Option1());
            binding.q3Option2.setText(Quiz.getQ3Option2());
            binding.q3Option3.setText(Quiz.getQ3Option3());
            binding.q3Option4.setText(Quiz.getQ3Option4());

            binding.Question4.setText(Quiz.getQuestion4());
            binding.q4Option1.setText(Quiz.getQ4Option1());
            binding.q4Option2.setText(Quiz.getQ4Option2());
            binding.q4Option3.setText(Quiz.getQ4Option3());
            binding.q4Option4.setText(Quiz.getQ4Option4());

            binding.Question5.setText(Quiz.getQuestion5());
            binding.q5Option1.setText(Quiz.getQ5Option());
            binding.q5Option2.setText(Quiz.getQ5Option2());
            binding.q5Option3.setText(Quiz.getQ5Option3());
            binding.q5Option4.setText(Quiz.getQ5Option4());


        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}