package com.degtyar.sergey.retrofitapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "http://api.github.com";
    private EditText input;
    Retrofit client;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        intent = new Intent(this, SecondActivity.class);
    }

    public void onGoClick(View view) {
        GitHubAPI service = client.create(GitHubAPI.class);
        String user = input.getText().toString();
        Call<GitHubUser> call = service.getUser(user);
        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {
                if(response.isSuccessful()) {
                    GitHubUser userData = response.body();
                    intent.putExtra("MY_IMG",userData.getAvatarUrl());
                    intent.putExtra("MY_TXT",
                            "Имя:       "+userData.getName() + " \n"+
                            "Логин:     "+userData.getLogin() + " \n"+
                            "Id:        "+userData.getId().toString() + " \n"+
                            "Email:     "+userData.getEmail() +" \n"+
                            "Followers: "+userData.getFollowers()+ " \n");
                    startActivity(intent);
                }
                else 
                {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {

            }
        });
    }
}
