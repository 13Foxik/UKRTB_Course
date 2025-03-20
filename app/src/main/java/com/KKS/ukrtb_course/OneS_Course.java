package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.KKS.ukrtb_course.databinding.ActivityOneScourseBinding;

public class OneS_Course extends AppCompatActivity {

    private ActivityOneScourseBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOneScourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
    public void BlockOneOnClick(View view){
        startActivity(new Intent(OneS_Course.this, EmptyStage.class));
        finish();
    }
    public void homeOnClick(View view){
        startActivity(new Intent(OneS_Course.this, Courses.class));
        finish();
    }
}