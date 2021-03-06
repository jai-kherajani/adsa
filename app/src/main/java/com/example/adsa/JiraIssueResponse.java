package com.example.adsa;

import java.io.Serializable;

import Model.Fields;

public class JiraIssueResponse implements Serializable {
    private String key;
    private Fields fields;

    public JiraIssueResponse(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "JiraIssueResponse{" +
                "key='" + key + '\'' +
                ", fields=" + fields +
                '}';
    }
}
