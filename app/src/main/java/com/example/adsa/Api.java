package com.example.adsa;



import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface Api {
    @GET("rest/api/3/issue/PROJ1-1")
    Call<JiraIssueResponse> getInfo();

    @GET("rest/api/3/search")
    Call<JiraAllAssignedIssuesToUserResponse> getAllAssignedIssuesToUser(
            @Query("jql") String jql
    );

}