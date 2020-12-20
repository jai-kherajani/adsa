package com.example.adsa;

import java.util.List;


public class JiraAllAssignedIssuesToUserResponse {
    private List<JiraIssueResponse> issues;

    public List<JiraIssueResponse> getIssues() {
        return issues;
    }

    public void setIssues(List<JiraIssueResponse> issues) {
        this.issues = issues;
    }


    @Override
    public String toString() {
        return "JiraAllAssignedIssuesToUserResponse{" +
                "issues=" + issues +
                '}';
    }
}
