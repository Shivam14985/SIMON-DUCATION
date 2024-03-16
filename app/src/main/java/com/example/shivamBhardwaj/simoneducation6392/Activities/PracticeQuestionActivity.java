package com.example.shivamBhardwaj.simoneducation6392.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.shivamBhardwaj.simoneducation6392.Adapters.HomeAdapter;
import com.example.shivamBhardwaj.simoneducation6392.Models.PracticeQuestionModel;
import com.example.shivamBhardwaj.simoneducation6392.R;
import com.example.shivamBhardwaj.simoneducation6392.databinding.ActivityPracticeQuestionBinding;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class PracticeQuestionActivity extends AppCompatActivity {
    ActivityPracticeQuestionBinding binding;
    FirebaseDatabase database;
    FirebaseStorage storage;
    Dialog progressDialog;
    ArrayList<PracticeQuestionModel> list = new ArrayList<>();
    HomeAdapter adapter = new HomeAdapter(list, this);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityPracticeQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        progressDialog = new Dialog(this);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.custom_progress_bar);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        if (b != null) {
            String j = (String) b.get("Subject");
            if (j.equals("General Knowledge")) {
                binding.tooltext.setText("General Knowledge");
                linearLayoutManager.setStackFromEnd(true);
                linearLayoutManager.setSmoothScrollbarEnabled(true);
                binding.rv.setLayoutManager(linearLayoutManager);
                binding.rv.setAdapter(adapter);
                database.getReference().child("Learning Question").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            String subject = snapshot1.child("descriptionSubject").getValue().toString();
                            if (subject.equals("General Knowledge")) {
                                PracticeQuestionModel model = snapshot1.getValue(PracticeQuestionModel.class);
                                model.setDescriptionId(snapshot1.getKey().toString());
                                database.getReference().child("Learning Question").child(snapshot1.getKey().toString()).setValue(model);
                                list.add(model);
                                progressDialog.dismiss();
                            } else {
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            if (j.equals("History")) {
                binding.tooltext.setText("History");
                linearLayoutManager.setStackFromEnd(true);
                linearLayoutManager.setSmoothScrollbarEnabled(true);
                binding.rv.setLayoutManager(linearLayoutManager);
                binding.rv.setAdapter(adapter);
                database.getReference().child("Learning Question").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            String subject = snapshot1.child("descriptionSubject").getValue().toString();
                            if (subject.equals("History")) {
                                PracticeQuestionModel model = snapshot1.getValue(PracticeQuestionModel.class);
                                model.setDescriptionId(snapshot1.getKey().toString());
                                database.getReference().child("Learning Question").child(snapshot1.getKey().toString()).setValue(model);
                                list.add(model);
                                progressDialog.dismiss();
                            } else {
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            if (j.equals("Maths")) {
                binding.tooltext.setText("Mathematics");
                linearLayoutManager.setStackFromEnd(true);
                linearLayoutManager.setSmoothScrollbarEnabled(true);
                binding.rv.setLayoutManager(linearLayoutManager);
                binding.rv.setAdapter(adapter);
                database.getReference().child("Learning Question").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            String subject = snapshot1.child("descriptionSubject").getValue().toString();
                            if (subject.equals("Mathematics")) {
                                PracticeQuestionModel model = snapshot1.getValue(PracticeQuestionModel.class);
                                model.setDescriptionId(snapshot1.getKey().toString());
                                database.getReference().child("Learning Question").child(snapshot1.getKey().toString()).setValue(model);
                                list.add(model);
                                progressDialog.dismiss();
                            } else {
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            if (j.equals("Sci")) {
                binding.tooltext.setText("Science");
                linearLayoutManager.setStackFromEnd(true);
                linearLayoutManager.setSmoothScrollbarEnabled(true);
                binding.rv.setLayoutManager(linearLayoutManager);
                binding.rv.setAdapter(adapter);
                database.getReference().child("Learning Question").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            String subject = snapshot1.child("descriptionSubject").getValue().toString();
                            if (subject.equals("Science")) {
                                PracticeQuestionModel model = snapshot1.getValue(PracticeQuestionModel.class);
                                model.setDescriptionId(snapshot1.getKey().toString());
                                database.getReference().child("Learning Question").child(snapshot1.getKey().toString()).setValue(model);
                                list.add(model);
                                progressDialog.dismiss();
                            } else {
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            if (j.equals("English")) {
                binding.tooltext.setText("English");
                linearLayoutManager.setStackFromEnd(true);
                linearLayoutManager.setSmoothScrollbarEnabled(true);
                binding.rv.setLayoutManager(linearLayoutManager);
                binding.rv.setAdapter(adapter);
                database.getReference().child("Learning Question").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            String subject = snapshot1.child("descriptionSubject").getValue().toString();
                            if (subject.equals("English")) {
                                PracticeQuestionModel model = snapshot1.getValue(PracticeQuestionModel.class);
                                model.setDescriptionId(snapshot1.getKey().toString());
                                database.getReference().child("Learning Question").child(snapshot1.getKey().toString()).setValue(model);
                                list.add(model);
                                progressDialog.dismiss();
                            } else {
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            if (j.equals("Geography")) {
                binding.tooltext.setText("Geography");
                linearLayoutManager.setStackFromEnd(true);
                linearLayoutManager.setSmoothScrollbarEnabled(true);
                binding.rv.setLayoutManager(linearLayoutManager);
                binding.rv.setAdapter(adapter);
                database.getReference().child("Learning Question").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            String subject = snapshot1.child("descriptionSubject").getValue().toString();
                            if (subject.equals("Geography")) {
                                PracticeQuestionModel model = snapshot1.getValue(PracticeQuestionModel.class);
                                model.setDescriptionId(snapshot1.getKey().toString());
                                database.getReference().child("Learning Question").child(snapshot1.getKey().toString()).setValue(model);
                                list.add(model);
                                progressDialog.dismiss();
                            } else {
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            if (j.equals("Economics")) {
                binding.tooltext.setText("Economics");
                linearLayoutManager.setStackFromEnd(true);
                linearLayoutManager.setSmoothScrollbarEnabled(true);
                binding.rv.setLayoutManager(linearLayoutManager);
                binding.rv.setAdapter(adapter);
                database.getReference().child("Learning Question").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            String subject = snapshot1.child("descriptionSubject").getValue().toString();
                            if (subject.equals("Economics")) {
                                PracticeQuestionModel model = snapshot1.getValue(PracticeQuestionModel.class);
                                model.setDescriptionId(snapshot1.getKey().toString());
                                database.getReference().child("Learning Question").child(snapshot1.getKey().toString()).setValue(model);
                                list.add(model);
                                progressDialog.dismiss();
                            } else {
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            if (j.equals("Facts")) {
                binding.tooltext.setText("Facts");
                linearLayoutManager.setStackFromEnd(true);
                linearLayoutManager.setSmoothScrollbarEnabled(true);
                binding.rv.setLayoutManager(linearLayoutManager);
                binding.rv.setAdapter(adapter);
                database.getReference().child("Learning Question").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            String subject = snapshot1.child("descriptionSubject").getValue().toString();
                            if (subject.equals("Facts")) {
                                PracticeQuestionModel model = snapshot1.getValue(PracticeQuestionModel.class);
                                model.setDescriptionId(snapshot1.getKey().toString());
                                database.getReference().child("Learning Question").child(snapshot1.getKey().toString()).setValue(model);
                                list.add(model);
                                progressDialog.dismiss();
                            } else {
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            if (j.equals("Computer")) {
                binding.tooltext.setText("Computer");
                linearLayoutManager.setStackFromEnd(true);
                linearLayoutManager.setSmoothScrollbarEnabled(true);
                binding.rv.setLayoutManager(linearLayoutManager);
                binding.rv.setAdapter(adapter);
                database.getReference().child("Learning Question").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            String subject = snapshot1.child("descriptionSubject").getValue().toString();
                            if (subject.equals("Computer")) {
                                PracticeQuestionModel model = snapshot1.getValue(PracticeQuestionModel.class);
                                model.setDescriptionId(snapshot1.getKey().toString());
                                database.getReference().child("Learning Question").child(snapshot1.getKey().toString()).setValue(model);
                                list.add(model);
                                progressDialog.dismiss();
                            } else {
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);
        binding.adView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                binding.adView.loadAd(adRequest);
            }
        });


        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //interstitial ads
                MobileAds.initialize(PracticeQuestionActivity.this, new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                        // on below line displaying a log that admob ads has been initialized.
                        Log.i("Admob", "Admob Initialized.");
                    }
                });
                AdRequest adRequest = new AdRequest.Builder().build();
                InterstitialAd.load(getApplicationContext(), "ca-app-pub-5928796239739806/2453621912", adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        Toast.makeText(PracticeQuestionActivity.this, "Fail to load ad..", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        super.onAdLoaded(interstitialAd);
                        interstitialAd.show(PracticeQuestionActivity.this);
                    }
                });

            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}