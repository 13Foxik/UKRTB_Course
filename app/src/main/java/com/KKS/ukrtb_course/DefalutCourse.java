package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.KKS.ukrtb_course.databinding.ActivityCoursesBinding;
import com.KKS.ukrtb_course.databinding.ActivityDefalutCourseBinding;

public class DefalutCourse extends AppCompatActivity {


    private ActivityDefalutCourseBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDefalutCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
    public void BlockOneOnClick(View view){
        startActivity(new Intent(DefalutCourse.this, DefaultCourseBlockOne.class));
        finish();
    }
}