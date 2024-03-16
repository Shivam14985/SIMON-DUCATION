package com.example.shivamBhardwaj.simoneducation6392.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.shivamBhardwaj.simoneducation6392.Activities.AddContentActivity;
import com.example.shivamBhardwaj.simoneducation6392.Models.UsersModel;
import com.example.shivamBhardwaj.simoneducation6392.R;
import com.example.shivamBhardwaj.simoneducation6392.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    FirebaseDatabase database;
    FirebaseStorage storage;
    Uri uri;
    FirebaseAuth auth;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/simon-education.appspot.com/o/App%20Photos%2Ftokens.png?alt=media&token=746cfcbd-db59-49c7-981a-8fb6af335f26").placeholder(R.drawable.gallery).into(binding.tokenimage);

        binding.tokenimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(getContext(), AddContentActivity.class);
//                startActivity(intent);
                UsersModel model=new UsersModel();
                database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UsersModel usersModel=snapshot.getValue(UsersModel.class);
                        model.setContribution(usersModel.getContribution()+10);
                        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("contribution").setValue(model.getContribution());
                        //Toast.makeText(getContext(), usersModel.getContribution(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        binding.editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 100);
            }
        });
        database.getReference().child("Users").child(auth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    UsersModel usersModel=snapshot.getValue(UsersModel.class);
                    Glide.with(getContext()).load(usersModel.getProfile()).placeholder(R.drawable.profile).into(binding.ProfileImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("Users").child(auth.getUid()).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.textView6.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("Users").child(auth.getUid()).child("contribution").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer i = Integer.parseInt(snapshot.getValue().toString());
                binding.contributionNumber.setText(i.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.editname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textView6.setVisibility(View.GONE);
                binding.editname.setVisibility(View.GONE);
                binding.editedname.setVisibility(View.VISIBLE);
                binding.SaveNAme.setVisibility(View.VISIBLE);
               }
        });
        binding.SaveNAme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.getReference().child("Users").child(auth.getUid()).child("name").setValue(binding.editedname.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "Name Updated Succesfully", Toast.LENGTH_SHORT).show();
                    }
                });
                binding.textView6.setVisibility(View.VISIBLE);
                binding.editname.setVisibility(View.VISIBLE);
                binding.editedname.setVisibility(View.GONE);
                binding.SaveNAme.setVisibility(View.GONE);

            }
        });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            if (data.getData() != null) {
                uri = data.getData();
                binding.ProfileImage.setImageURI(uri);
                StorageReference reference = storage.getReference().child("Profiles").child(FirebaseAuth.getInstance().getUid());
                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                database.getReference().child("Users")
                                        .child(auth.getUid()).child("Profile")
                                        .setValue(uri.toString());
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }
    }
}