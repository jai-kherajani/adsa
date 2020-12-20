package com.example.adsa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

public class IdCardActivity extends AppCompatActivity {

    private static final String TAG = "ID Card Activity";
    private TextView email, id, name, mobile;
    private ImageView profile;
    private FloatingActionButton signOut, resetPassword;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_card);

        Toolbar myToolbar = findViewById(R.id.id_card_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email = findViewById(R.id.employee_email_id);
        id = findViewById(R.id.employee_id);
        name = findViewById(R.id.employee_name);
        profile = findViewById(R.id.employee_picture);
        mobile = findViewById(R.id.employee_mobile);
        resetPassword = findViewById(R.id.reset_password_button);
        signOut = findViewById(R.id.sign_out_button);

        ADSAApplication adsaApplication = (ADSAApplication) getApplicationContext();
        HashMap<String, String> values = adsaApplication.getData();
        id.setText(String.valueOf(values.get("id")));
        name.setText(values.get("first_name") + " " + values.get("last_name"));
        email.setText(values.get("email"));
        mobile.setText(values.get("mobile"));

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        resetPassword.setOnClickListener(v -> resetPassword());
        signOut.setOnClickListener(v -> signOut());
    }

    private void resetPassword() {
        firebaseAuth.sendPasswordResetEmail(firebaseUser.getEmail()).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "Email sent.");
                Toast.makeText(IdCardActivity.this, "\uD83C\uDF89 Email containing password reset link has been sent to the above email id. \uD83C\uDF89", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(IdCardActivity.this, "☹️ Couldn't send password reset email!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void signOut() {
        firebaseAuth.signOut();
        IdCardActivity.this.finish();
        startActivity(new Intent(IdCardActivity.this, LogInActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}