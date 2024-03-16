package com.example.shivamBhardwaj.simoneducation6392.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.shivamBhardwaj.simoneducation6392.Models.UsersModel;
import com.example.shivamBhardwaj.simoneducation6392.R;
import com.example.shivamBhardwaj.simoneducation6392.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    String phoneNumber, otp;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    boolean doubleBackToExitPressedOnce = false;
    private String verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing In");
        StartFirebaseLogin();

        binding.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                final Animation animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.bounce);
                final Animation animation1 = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.bonceexit);

                binding.signInBtn.startAnimation(animation);
                binding.signInBtn.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.signInBtn.startAnimation(animation1);
                    }
                }, 0);
                String Email = binding.EtEmail.getText().toString();
                String Password = binding.EtPassword.getText().toString();
                auth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        binding.SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        binding.ForGotTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.SignInLAyout.setVisibility(View.GONE);
                binding.ForgotPAssWordLAyout.setVisibility(View.VISIBLE);
            }
        });
        binding.GoToOTpSignIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.SignInLAyout.setVisibility(View.GONE);
                binding.SignInWithOtpLayout.setVisibility(View.VISIBLE);
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.SignInWithOtpLayout.setVisibility(View.GONE);
                binding.SignInLAyout.setVisibility(View.VISIBLE);
            }
        });
        binding.GOBAck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.SignInLAyout.setVisibility(View.VISIBLE);
                binding.ForgotPAssWordLAyout.setVisibility(View.GONE);
            }
        });
        binding.ResetPAssword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = binding.EtEmailForgot.getText().toString();
                if (Email.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Enter Email First", Toast.LENGTH_SHORT).show();
                } else {
                    auth.sendPasswordResetEmail(Email).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(LoginActivity.this, "Reset password link has send to your Email ", Toast.LENGTH_LONG).show();
                            binding.SignInLAyout.setVisibility(View.VISIBLE);
                            binding.ForgotPAssWordLAyout.setVisibility(View.GONE);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        binding.SendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.bounce);
                final Animation animation1 = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.bonceexit);
                binding.SendOtp.startAnimation(animation);
                binding.SendOtp.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        binding.SendOtp.startAnimation(animation1);
                    }
                }, 0);
                binding.Success.setVisibility(View.GONE);
                binding.Failed.setVisibility(View.GONE);
                binding.progressBar.setVisibility(View.VISIBLE);
                phoneNumber = binding.Phone.getText().toString();
                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + phoneNumber,                // Phone number to verify
                        60,                           // Timeout duration
                        TimeUnit.SECONDS,                // Unit of timeout
                        LoginActivity.this,        // Activity (for callback binding)
                        mCallback);                      // OnVerificationStateChangedCallbacks
            }
        });

        binding.SubmitOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                otp = binding.otpsubmit.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);
                SigninWithPhone(credential);
            }
        });
        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void SigninWithPhone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    database.getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                UsersModel usersModel=new UsersModel();
                                usersModel.setNumber(phoneNumber);
                                usersModel.setName(binding.EtName.getText().toString());
                                usersModel.setPartner(false);
                                database.getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(usersModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void StartFirebaseLogin() {

        auth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(LoginActivity.this, "verification completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                binding.progressBar.setVisibility(View.GONE);
                binding.Failed.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                binding.progressBar.setVisibility(View.GONE);
                binding.Success.setVisibility(View.VISIBLE);
                binding.otpsubmitt.setVisibility(View.VISIBLE);
                binding.SubmitOtp.setVisibility(View.VISIBLE);
                Toast.makeText(LoginActivity.this, "Code sent to " + phoneNumber, Toast.LENGTH_LONG).show();
            }
        };
    }

    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}