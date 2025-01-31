package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.KKS.ukrtb_course.databinding.ActivityEmptyStageBinding;
import com.KKS.ukrtb_course.databinding.ActivityUnityB1Binding;

public class EmptyStage extends AppCompatActivity {

    private ActivityEmptyStageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmptyStageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    public void homeOnClick(View view){
        startActivity(new Intent(EmptyStage.this, Courses.class));
        finish();
    }
}