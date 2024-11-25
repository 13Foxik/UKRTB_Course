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
import com.KKS.ukrtb_course.databinding.ActivityDefaultCourseBlockOneBinding;

public class DefaultCourseBlockOne extends AppCompatActivity {

    private ActivityDefaultCourseBlockOneBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDefaultCourseBlockOneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
    public void StageOneOnClick(View view){
        startActivity(new Intent(DefaultCourseBlockOne.this, DefaultCourseBlockOneStageOne.class));
        finish();
    }
}