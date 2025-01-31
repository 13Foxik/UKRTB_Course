package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.KKS.ukrtb_course.databinding.ActivityUnityCourseBinding;


public class UnityCourse extends AppCompatActivity {


    private ActivityUnityCourseBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUnityCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
    public void BlockOneOnClick(View view){
        startActivity(new Intent(UnityCourse.this, UnityB1.class));
        finish();
    }
    public void homeOnClick(View view){
        startActivity(new Intent(UnityCourse.this, Courses.class));
        finish();
    }
}