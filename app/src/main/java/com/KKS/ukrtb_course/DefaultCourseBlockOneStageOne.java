package com.KKS.ukrtb_course;

import android.os.Bundle;

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
}