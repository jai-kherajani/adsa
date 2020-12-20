package com.example.adsa;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JiraActivity extends AppCompatActivity {

    private EditText issueId;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jira);


        Toolbar myToolbar = findViewById(R.id.jira_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        issueId = findViewById(R.id.issueId);
        searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(v -> {
            findIssueById(issueId.getText().toString());
        });


    }

    private void findIssueById(String issueId) {

        if (!TextUtils.isEmpty(issueId)) {

            Call<JiraIssueResponse> call = RetrofitClient.getInstance().getApi().getInfo(issueId);

            call.enqueue(new Callback<JiraIssueResponse>() {
                @Override
                public void onResponse(Call<JiraIssueResponse> call, Response<JiraIssueResponse> response) {
                    String s = null;
                    s = response.body().toString();
                    Log.w("JiraIssueResponse", s);
                    //   Toast.makeText(JiraActivity.this, s, Toast.LENGTH_LONG).show();
                    JiraIssueResponse jiraIssueResponse = response.body();
                    if (jiraIssueResponse != null) {
                        Intent i = new Intent(JiraActivity.this, IssueDetails.class);
                        i.putExtra("jiraIssueResponse", jiraIssueResponse);
                        startActivity(i);
                    }
                }

                @Override
                public void onFailure(Call<JiraIssueResponse> call, Throwable t) {
                    String m = t.getMessage();
                    Log.w("RESPONSE FAIL", m);
                    //  Toast.makeText(JiraActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });

        }


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

