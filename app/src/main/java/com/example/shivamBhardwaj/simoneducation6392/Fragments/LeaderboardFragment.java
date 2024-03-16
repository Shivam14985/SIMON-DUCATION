package com.example.shivamBhardwaj.simoneducation6392.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.shivamBhardwaj.simoneducation6392.Activities.LoginActivity;
import com.example.shivamBhardwaj.simoneducation6392.Adapters.LeaderBoardAdapter;
import com.example.shivamBhardwaj.simoneducation6392.Models.PracticeQuestionModel;
import com.example.shivamBhardwaj.simoneducation6392.Models.QuizModel;
import com.example.shivamBhardwaj.simoneducation6392.Models.UsersModel;
import com.example.shivamBhardwaj.simoneducation6392.R;
import com.example.shivamBhardwaj.simoneducation6392.databinding.FragmentLeaderboardBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class LeaderboardFragment extends Fragment {

    private FragmentLeaderboardBinding binding;
    FirebaseDatabase database;
    FirebaseStorage storage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLeaderboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        ArrayList<UsersModel>list=new ArrayList<>();
        LeaderBoardAdapter adapter=new LeaderBoardAdapter(list,getContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.lederrecycler.setLayoutManager(linearLayoutManager);
        binding.lederrecycler.setAdapter(adapter);

        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    UsersModel model= dataSnapshot.getValue(UsersModel.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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