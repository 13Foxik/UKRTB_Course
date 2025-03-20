package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.KKS.ukrtb_course.databinding.ActivityRobotechnologyBinding;

public class Robotechnology extends AppCompatActivity {

    private ActivityRobotechnologyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRobotechnologyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
    public void BlockOneOnClick(View view){
        startActivity(new Intent(Robotechnology.this, EmptyStage.class));
        finish();
    }
    public void homeOnClick(View view){
        startActivity(new Intent(Robotechnology.this, Courses.class));
        finish();
    }
}