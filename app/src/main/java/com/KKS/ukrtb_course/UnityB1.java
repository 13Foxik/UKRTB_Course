package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.KKS.ukrtb_course.databinding.ActivityUnityB1Binding;


public class UnityB1 extends AppCompatActivity {

    private ActivityUnityB1Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUnityB1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
    public void StageOneOnClick(View view){
        startActivity(new Intent(UnityB1.this, UnityB1S1P1.class));
        finish();
    }
    public void StageTwoOnClick(View view){
        startActivity(new Intent(UnityB1.this, UnityB1S2P1.class));
        finish();
    }
    public void StageThreeOnClick(View view){
        startActivity(new Intent(UnityB1.this, UnityB1S3P1.class));
        finish();
    }
    public void StageFourOnClick(View view){
        startActivity(new Intent(UnityB1.this, UnityB1S4P1.class));
        finish();
    }
    public void homeOnClick(View view){
        startActivity(new Intent(UnityB1.this, UnityCourse.class));
        finish();
    }
}