package com.example.chatbox;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.concurrent.TimeUnit;

// Activity for OTP verification during login
public class login_OTP extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText OTPEditText;
    private Button verifyOTP_btn;
    private ProgressBar login_progressBar;
    private String verificationId;
    private String phoneNumber;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private PhoneAuthProvider.ForceResendingToken resendingToken;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Dialog loading2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_otp);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        loading2=new Dialog(this);
        loading2.setContentView(R.layout.loading2);
        loading2.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        loading2.getWindow().setBackgroundDrawable(getDrawable(R.drawable.roundcorners));

        loading2.setCancelable(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        login_progressBar = findViewById(R.id.loginProgressBar);
        login_progressBar.setVisibility(View.GONE);
        OTPEditText = findViewById(R.id.otp);
        verifyOTP_btn = findViewById(R.id.verifyOTP_btn);
        phoneNumber = getIntent().getStringExtra("phoneNumber").toString();
        sendOTP(phoneNumber);

        verifyOTP_btn.setOnClickListener(v -> {
            String enteredOTP = OTPEditText.getText().toString().trim();

            if (verificationId != null && !verificationId.isEmpty()) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, enteredOTP);

                mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loading2.show(); // Show loading dialog
                            checkIfUserExists(phoneNumber);
                        } else {
                            Toast.makeText(getApplicationContext(), "OTP verification failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Verification ID is null or empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Check if the user exists in the database
    private void checkIfUserExists(String phoneNumber) {
        db.collection("Registration")
                .whereEqualTo("phone", phoneNumber)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (!task.getResult().isEmpty()) {
                                // User exists in the database, redirect to ChatsListActivity
                                Intent intent = new Intent(login_OTP.this, ChatsListActivity.class);
                                intent.putExtra("phoneNumber", phoneNumber);
                                startActivity(intent);
                                finish();
                            } else {
                                // User does not exist, redirect to login_userName
                                Intent intent = new Intent(login_OTP.this, login_userName.class);
                                intent.putExtra("phoneNumber", phoneNumber);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            // Handle error
                            Toast.makeText(login_OTP.this, "Error checking user existence", Toast.LENGTH_SHORT).show();
                        }
                        loading2.dismiss(); // Dismiss loading dialog
                    }
                });
    }

    // Verify OTP entered by the user
    private void verifyOTP(PhoneAuthCredential credential) {
        loading2.show(); // Show loading dialog before verifying OTP
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(login_OTP.this, "OTP verification successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login_OTP.this, login_userName.class);
                    intent.putExtra("phoneNumber", phoneNumber);
                    startActivity(intent);
                    finish(); // Optional: Close this activity upon successful verification
                } else {
                    Toast.makeText(getApplicationContext(), "OTP verification failed", Toast.LENGTH_SHORT).show();
                }
                loading2.dismiss(); // Dismiss loading dialog after OTP verification is completed
            }
        });
    }

    // Send OTP to the user's phone number
    private void sendOTP(String phoneNumber) {
        PhoneAuthOptions.Builder builder = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber("+977" + phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        verifyOTP(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        loading2.dismiss(); // Dismiss loading dialog if OTP verification fails
                        Toast.makeText(getApplicationContext(), "OTP verification failed", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationId = s;
                        resendingToken = forceResendingToken;
                    }
                });
        PhoneAuthProvider.verifyPhoneNumber(builder.build());
    }

}
