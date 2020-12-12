package com.example.adsa;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class LogInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailField, passwordField;
    private Button signinButton, forgotPassword;
    private final String TAG = "SIGN IN PROCESS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        signinButton = findViewById(R.id.signin);
        forgotPassword = findViewById(R.id.forgotPassword);

        signinButton.setOnClickListener(v -> {
            attemptSignIn();
        });

        forgotPassword.setOnClickListener(v -> {
            resetPassword();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Toast.makeText(LogInActivity.this, "\uD83D\uDC4B Hello " + currentUser.getEmail(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LogInActivity.this, HomeActivity.class));
            LogInActivity.this.finish();
        }
    }

    private void attemptSignIn() {
        if (validateForm(false)) {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LogInActivity.this, "☹️ Authentication failed!",
                                        Toast.LENGTH_LONG).show();
                                emailField.setText("");
                                passwordField.setText("");
                                updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    }

    private void resetPassword() {
        if (validateForm(true)) {
            mAuth.sendPasswordResetEmail(emailField.getText().toString())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                            Toast.makeText(LogInActivity.this, "\uD83C\uDF89 Email containing password reset link has been sent to the above email id. \uD83C\uDF89", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            emailField.setText("");
                            passwordField.setText("");
                            Toast.makeText(LogInActivity.this, "☹️ Couldn't send password reset email!",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

    private boolean validateForm(boolean isPasswordReset) {
        boolean isValid = true;
        String email = emailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailField.setError("Required.");
            isValid = false;
        } else if (!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")){
            emailField.setError("Invalid Email.");
        }
        else {
            emailField.setError(null);
        }
        if (!isPasswordReset) {
            String password = passwordField.getText().toString();
            if (TextUtils.isEmpty(password)) {
                passwordField.setError("Required.");
                isValid = false;
            } else if (password.length() < 6) {
                passwordField.setError("Must be atleast 6 characters");
                isValid = false;
            } else {
                passwordField.setError(null);
            }
        }
        return isValid;
    }
}