package com.degtyar.sergey.retrofitapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by student on 05.12.2016.
 */

public class SecondActivity extends Activity{
private ImageView img;
private TextView output;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        img = (ImageView) findViewById(R.id.logo);
        output = (TextView) findViewById(R.id.text_v);
        Intent intent = getIntent();
        Picasso.with(this).load(intent.getStringExtra("MY_IMG")).into(img);
        output.setText(intent.getStringExtra("MY_TXT"));

    }
}
