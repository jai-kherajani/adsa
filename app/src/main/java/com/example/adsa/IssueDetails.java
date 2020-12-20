package com.example.adsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class IssueDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_details);

        Toolbar myToolbar = findViewById(R.id.issues_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        JiraIssueResponse jiraIssueResponse = (JiraIssueResponse) getIntent().getSerializableExtra("jiraIssueResponse");

        TextView issueId = findViewById(R.id.issueID);
        issueId.setText(jiraIssueResponse.getKey());

        TextView issueType = findViewById(R.id.issuetype);
        issueType.setText(jiraIssueResponse.getFields().getIssuetype().getName());

        TextView assignee = findViewById(R.id.assignee);
        assignee.setText(jiraIssueResponse.getFields().getAssignee().getDisplayName());

        TextView reporter = findViewById(R.id.reporter);
        reporter.setText(jiraIssueResponse.getFields().getReporter().getDisplayName());

        TextView description = findViewById(R.id.description);
        description.setText(jiraIssueResponse.getFields().getDescription().getContent().get(0).getContent().get(0).getText());

        TextView status = findViewById(R.id.status);
        status.setText(jiraIssueResponse.getFields().getStatus().getName());

        TextView priority = findViewById(R.id.priority);
        priority.setText(jiraIssueResponse.getFields().getPriority().getName());

        TextView createdOn = findViewById(R.id.createdOn);
        createdOn.setText(jiraIssueResponse.getFields().getCreated().toString());

        TextView comments = findViewById(R.id.comments);
        comments.setText(jiraIssueResponse.getFields().getComment().getComments().get(0).getBody().getContent().get(0).getContent().get(0).getText());

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