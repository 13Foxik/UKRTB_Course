package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.KKS.ukrtb_course.databinding.ActivityDronCourseBinding;

public class Dron_Course extends AppCompatActivity {

    private ActivityDronCourseBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDronCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
    public void BlockOneOnClick(View view){
        startActivity(new Intent(Dron_Course.this, EmptyStage.class));
        finish();
    }
    public void homeOnClick(View view){
        startActivity(new Intent(Dron_Course.this, Courses.class));
        finish();
    }
}