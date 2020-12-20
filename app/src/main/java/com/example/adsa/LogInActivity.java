package com.example.adsa;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity {

    private final String TAG = "SIGN IN PROCESS";
    private FirebaseAuth mAuth;
    private EditText emailField, passwordField;
    private FloatingActionButton signinButton, forgotPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar myToolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(myToolbar);

        mAuth = FirebaseAuth.getInstance();

        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        signinButton = findViewById(R.id.sign_in_button);
        forgotPassword = findViewById(R.id.reset_password_button);
        progressBar = findViewById(R.id.progress);

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
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("Users/").child(currentUser.getUid());
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    HashMap<String, String> values = (HashMap<String, String>) snapshot.getValue();
                    ADSAApplication adsaApplication = (ADSAApplication) getApplicationContext();
                    adsaApplication.setData(values);
                    Toast.makeText(LogInActivity.this, "\uD83D\uDC4B Hello " + values.get("first_name"), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(LogInActivity.this, "Interrupted by User!", Toast.LENGTH_SHORT).show();
                }
            });
            hideProgressBar();
            startActivity(new Intent(LogInActivity.this, HomeActivity.class));
           // startActivity(new Intent(LogInActivity.this, JiraActivity.class));
            LogInActivity.this.finish();
        }
    }

    private void attemptSignIn() {
        showProgressBar();

  /*      Call<JiraIssueResponse> call = RetrofitClient.getInstance().getApi().getInfo("PROJ1-1");

        call.enqueue(new Callback<JiraIssueResponse>() {
            @Override
            public void onResponse(Call<JiraIssueResponse> call, Response<JiraIssueResponse> response) {
                String s = null;
                s = response.body().toString();
                Log.w("JiraIssueResponse", s);
                Toast.makeText(LogInActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<JiraIssueResponse> call, Throwable t) {
                String m = t.getMessage();
                Log.w(TAG, "RESPONSE FAIL", t);
                Toast.makeText(LogInActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

*/

// ---------******-----------------------------------------------********-------------------------------------------------*****


/*
        Call<JiraAllAssignedIssuesToUserResponse> call2 = RetrofitClient.getInstance().getApi().getAllAssignedIssuesToUser("assignee=currentuser()");

        call2.enqueue(new Callback<JiraAllAssignedIssuesToUserResponse>() {
            @Override
            public void onResponse(Call<JiraAllAssignedIssuesToUserResponse> call2, Response<JiraAllAssignedIssuesToUserResponse> response) {
                String s = null;
                s = response.body().toString();
                Log.w("JiraAllAssignedIssuesToUserResponse", s);
              //  Toast.makeText(LogInActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<JiraAllAssignedIssuesToUserResponse> call, Throwable t) {
                String m = t.getMessage();
                Log.w(TAG, "RESPONSE FAIL", t);
                //Toast.makeText(LogInActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });


*/

// ---------******-----------------------------------------------********-------------------------------------------------*****




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
                                hideProgressBar();
                                updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    }

    private void resetPassword() {
        showProgressBar();
        if (validateForm(true)) {
            mAuth.sendPasswordResetEmail(emailField.getText().toString())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                            Toast.makeText(LogInActivity.this, "\uD83C\uDF89 Email containing password reset link has been sent to the above email id. \uD83C\uDF89", Toast.LENGTH_LONG).show();
                        } else {
                            emailField.setText("");
                            passwordField.setText("");
                            Toast.makeText(LogInActivity.this, "☹️ Couldn't send password reset email!",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        }
        hideProgressBar();
    }

    private boolean validateForm(boolean isPasswordReset) {
        boolean isValid = true;
        String email = emailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailField.setError("Required.");
            isValid = false;
        } else if (!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            emailField.setError("Invalid Email.");
        } else {
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

    void showProgressBar() {
        progressBar.isIndeterminate();
        signinButton.setVisibility(View.GONE);
        forgotPassword.setVisibility(View.GONE);
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    void hideProgressBar() {
        progressBar.setVisibility(ProgressBar.GONE);
        signinButton.setVisibility(View.VISIBLE);
        forgotPassword.setVisibility(View.VISIBLE);
    }
}