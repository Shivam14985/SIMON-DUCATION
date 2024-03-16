package com.example.shivamBhardwaj.simoneducation6392.Activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.shivamBhardwaj.simoneducation6392.Models.UsersModel;
import com.example.shivamBhardwaj.simoneducation6392.R;
import com.example.shivamBhardwaj.simoneducation6392.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    String phoneNumber, otp;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    private String verificationCode;
    boolean doubleBackToExitPressedOnce =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        StartFirebaseLogin();
        getSupportActionBar().hide();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering");

        //Date Picker
        binding.EtDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are adding click listener
                // for our pick date button
                binding.EtDOB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // on below line we are getting
                        // the instance of our calendar.
                        final Calendar c = Calendar.getInstance();

                        // on below line we are getting
                        // our day, month and year.
                        int year = c.get(Calendar.YEAR) - 18;
                        int month = c.get(Calendar.MONTH);
                        int day = c.get(Calendar.DAY_OF_MONTH);

                        // on below line we are creating a variable for date picker dialog.
                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                // on below line we are passing context.
                                SignUpActivity.this,
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {
                                        // on below line we are setting date to our edit text.
                                        binding.EtDOB.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                    }
                                },
                                // on below line we are passing year,
                                // month and day for selected date in our date picker.
                                year, month, day);
                        // at last we are calling show to
                        // display our date picker dialog.
                        datePickerDialog.show();
                    }
                });
            }
        });

        //Sign up button
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(SignUpActivity.this, R.anim.bounce);
                final Animation animation1 = AnimationUtils.loadAnimation(SignUpActivity.this, R.anim.bonceexit);

                binding.signup.startAnimation(animation);
                binding.signup.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.signup.startAnimation(animation1);
                    }
                }, 0);
                String Email = binding.EtEmail.getText().toString();
                String Dob = binding.EtDOB.getText().toString();
                String Name = binding.EtName.getText().toString();
                String Password = binding.EtPassword.getText().toString();
                int Notification=0;
                int Leaderboard=0;
                int Contribution=0;
                boolean Partner = false;
                if (Email.isEmpty() && Dob.isEmpty() && Name.isEmpty() && Password.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Fill All Details", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.show();
                    auth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(SignUpActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                                binding.LayoutForEmailAuthentication.setVisibility(View.GONE);
                                binding.registeredsuccess.setVisibility(View.VISIBLE);
                                binding.registeredsuccess.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        UsersModel model=new UsersModel(Email,Dob,Name,phoneNumber,Partner);
                                        model.setContribution(Notification);
                                        model.setLearboardRank(Leaderboard);
                                        model.setNotificationCount(Notification);
                                        String id = task.getResult().getUser().getUid();
                                        database.getReference().child("Users").child(id).setValue(model);
                                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                }, 4300);
                            }
                        }
                    });
                }
            }
        });
        binding.AuthPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(SignUpActivity.this, R.anim.bounce);
                final Animation animation1 = AnimationUtils.loadAnimation(SignUpActivity.this, R.anim.bonceexit);
                binding.AuthPhone.startAnimation(animation);
                binding.AuthPhone.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.AuthPhone.startAnimation(animation1);
                    }
                }, 0);
                binding.LayoutForEmailAuthentication.setVisibility(View.GONE);
                binding.layout.setVisibility(View.VISIBLE);
            }
        });
        binding.logintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.SingUpUsingEmil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change the background color temporarily
                int originalColor = Color.parseColor("#FFFFFFFF");
                int clickedColor = Color.parseColor("#E8E8E8"); // Replace with your desired color

                // Change background color when clicked
                binding.SingUpUsingEmil.setBackgroundColor(clickedColor);

                // Set a delayed runnable to revert the color after a short duration (e.g., 500 milliseconds)
                binding.SingUpUsingEmil.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.SingUpUsingEmil.setBackgroundColor(originalColor);
                    }
                }, 100);
                binding.LayoutForEmailAuthentication.setVisibility(View.VISIBLE);
                binding.layout.setVisibility(View.GONE);
            }
        });
        binding.SendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(SignUpActivity.this, R.anim.bounce);
                final Animation animation1 = AnimationUtils.loadAnimation(SignUpActivity.this, R.anim.bonceexit);
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
                        SignUpActivity.this,        // Activity (for callback binding)
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
                    progressDialog.dismiss();
                    binding.registeredsuccess.setVisibility(View.VISIBLE);
                    binding.layout.setVisibility(View.GONE);
                    binding.registeredsuccess.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SignUpActivity.this, "Welcome" + binding.EtName.getText().toString(), Toast.LENGTH_LONG).show();
                            UsersModel usersModel=new UsersModel();
                            usersModel.setNumber(phoneNumber);
                            usersModel.setName(binding.EtName.getText().toString());
                            usersModel.setPartner(false);
                            database.getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(usersModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Intent intent = new Intent(
                                            SignUpActivity.this, MainActivity.class);
                                    startActivity(intent);

                                }
                            });
                            }
                    }, 4500);
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(SignUpActivity.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void StartFirebaseLogin() {

        auth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(SignUpActivity.this, "verification completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(SignUpActivity.this, "Code sent to " + phoneNumber, Toast.LENGTH_LONG).show();
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