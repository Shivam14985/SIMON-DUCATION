package com.example.shivamBhardwaj.simoneducation6392.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.shivamBhardwaj.simoneducation6392.Activities.LoginActivity;
import com.example.shivamBhardwaj.simoneducation6392.Activities.PracticeQuestionActivity;
import com.example.shivamBhardwaj.simoneducation6392.R;
import com.example.shivamBhardwaj.simoneducation6392.databinding.FragmentHomeBinding;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class HomeFragment extends Fragment {

    FirebaseDatabase database;
    Uri uri;
    FirebaseStorage storage;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);
        binding.adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                binding.adView.setVisibility(View.VISIBLE);
            }
        });
        binding.signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/simon-education.appspot.com/o/App%20Photos%2F20240306_104036_0000.png?alt=media&token=ad2700dd-c1ef-4587-851f-f341ed466108").placeholder(R.drawable.gallery).into(binding.GK);
        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/simon-education.appspot.com/o/App%20Photos%2F20240306_104201_0000.png?alt=media&token=09a11d7b-a30c-4253-9e4d-e7557bd95af4").placeholder(R.drawable.gallery).into(binding.Science);
        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/simon-education.appspot.com/o/App%20Photos%2F20240306_104227_0000.png?alt=media&token=979b4b6d-1c6d-4ef5-8b16-ff3996375511").placeholder(R.drawable.gallery).into(binding.History);
        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/simon-education.appspot.com/o/App%20Photos%2F20240306_104329_0000.png?alt=media&token=34cc7fe7-6428-4399-9505-2f11b72d0fdd").placeholder(R.drawable.gallery).into(binding.Maths);
        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/simon-education.appspot.com/o/App%20Photos%2F20240308_084527_0000.png?alt=media&token=95c68747-33e1-48ee-8431-2622b0278cec").placeholder(R.drawable.gallery).into(binding.English);
        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/simon-education.appspot.com/o/App%20Photos%2F20240308_084648_0000.png?alt=media&token=0562f504-d0fc-4f98-9ee5-74537fb63ee7").placeholder(R.drawable.gallery).into(binding.Geography);
        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/simon-education.appspot.com/o/App%20Photos%2F20240308_090202_0000.png?alt=media&token=3644ca42-0624-4086-ad20-cd0b40a005b6").placeholder(R.drawable.gallery).into(binding.Facts);
        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/simon-education.appspot.com/o/App%20Photos%2F20240308_090320_0000.png?alt=media&token=d3072cbb-0fcf-4f04-83df-5782d4bfdf52").placeholder(R.drawable.gallery).into(binding.Economics);
        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/simon-education.appspot.com/o/App%20Photos%2F20240308_090416_0000.png?alt=media&token=f73ab577-a3e2-4fef-aba4-0915c6ec0560").placeholder(R.drawable.gallery).into(binding.Computer);

        binding.GK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                final Animation animation1 = AnimationUtils.loadAnimation(getContext(), R.anim.bonceexit);

                binding.GK.startAnimation(animation);
                binding.GK.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.GK.startAnimation(animation1);
                    }
                }, 0);
                Intent intent = new Intent(getContext(), PracticeQuestionActivity.class);
                intent.putExtra("Subject", "General Knowledge");
                startActivity(intent);
            }
        });
        binding.History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                final Animation animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.bonceexit);
                binding.History.startAnimation(animation);
                binding.History.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.History.startAnimation(animation2);
                    }
                }, 0);
                Intent intent = new Intent(getContext(), PracticeQuestionActivity.class);
                intent.putExtra("Subject", "History");
                startActivity(intent);
            }
        });
        binding.Maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                final Animation animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.bonceexit);
                binding.Maths.startAnimation(animation);
                binding.Maths.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.Maths.startAnimation(animation2);
                    }
                }, 0);
                Intent intent = new Intent(getContext(), PracticeQuestionActivity.class);
                intent.putExtra("Subject", "Maths");
                startActivity(intent);
            }
        });
        binding.Science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                final Animation animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.bonceexit);
                binding.Science.startAnimation(animation);
                binding.Science.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.Science.startAnimation(animation2);
                    }
                }, 0);
                Intent intent = new Intent(getContext(), PracticeQuestionActivity.class);
                intent.putExtra("Subject", "Sci");
                startActivity(intent);
            }
        });
        binding.English.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                final Animation animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.bonceexit);
                binding.English.startAnimation(animation);
                binding.English.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.English.startAnimation(animation2);
                    }
                }, 0);
                Intent intent = new Intent(getContext(), PracticeQuestionActivity.class);
                intent.putExtra("Subject", "English");
                startActivity(intent);
            }
        });
        binding.Geography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                final Animation animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.bonceexit);
                binding.Geography.startAnimation(animation);
                binding.Geography.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.Geography.startAnimation(animation2);
                    }
                }, 0);
                Intent intent = new Intent(getContext(), PracticeQuestionActivity.class);
                intent.putExtra("Subject", "Geography");
                startActivity(intent);
            }
        });
        binding.Economics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                final Animation animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.bonceexit);
                binding.Economics.startAnimation(animation);
                binding.Economics.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.Economics.startAnimation(animation2);
                    }
                }, 0);
                Intent intent = new Intent(getContext(), PracticeQuestionActivity.class);
                intent.putExtra("Subject", "Economics");
                startActivity(intent);
            }
        });
        binding.Facts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                final Animation animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.bonceexit);
                binding.Facts.startAnimation(animation);
                binding.Facts.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.Facts.startAnimation(animation2);
                    }
                }, 0);
                Intent intent = new Intent(getContext(), PracticeQuestionActivity.class);
                intent.putExtra("Subject", "Facts");
                startActivity(intent);
            }
        });
        binding.Computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                final Animation animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.bonceexit);
                binding.Computer.startAnimation(animation);
                binding.Computer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.Computer.startAnimation(animation2);
                    }
                }, 0);
                Intent intent = new Intent(getContext(), PracticeQuestionActivity.class);
                intent.putExtra("Subject", "Computer");
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}