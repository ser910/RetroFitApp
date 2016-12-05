package com.degtyar.sergey.retrofitapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by student on 05.12.2016.
 */

public interface GitHubAPI {
    @GET("/users/{username}")
    Call<GitHubUser> getUser(@Path("username")  String username);
}
