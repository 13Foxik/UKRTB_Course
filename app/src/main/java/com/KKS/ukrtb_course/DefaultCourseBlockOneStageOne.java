package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.KKS.ukrtb_course.databinding.ActivityDefalutCourseBinding;
import com.KKS.ukrtb_course.databinding.ActivityDefaultCourseBlockOneStageOneBinding;

public class DefaultCourseBlockOneStageOne extends AppCompatActivity {

    private ActivityDefaultCourseBlockOneStageOneBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDefaultCourseBlockOneStageOneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void nextOnClick(View view){
        startActivity(new Intent(DefaultCourseBlockOneStageOne.this, DefaultPage2.class));
        finish();
    }
    public void homeOnClick(View view){
        startActivity(new Intent(DefaultCourseBlockOneStageOne.this, DefaultCourseBlockOne.class));
        finish();
    }
}