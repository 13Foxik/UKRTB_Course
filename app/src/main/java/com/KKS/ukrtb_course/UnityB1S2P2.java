package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.KKS.ukrtb_course.databinding.ActivityUnityB1S2P2Binding;

public class UnityB1S2P2 extends AppCompatActivity {

    private ActivityUnityB1S2P2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUnityB1S2P2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
    public void nextOnClick(View view){
        startActivity(new Intent(UnityB1S2P2.this, UnityB1S2P3.class));
        finish();
    }
    public void backOnClick(View view){
        startActivity(new Intent(UnityB1S2P2.this, UnityB1S2P1.class));
        finish();
    }
    public void homeOnClick(View view){
        startActivity(new Intent(UnityB1S2P2.this, UnityB1.class));
        finish();
    }
}