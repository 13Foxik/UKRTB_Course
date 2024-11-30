package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.KKS.ukrtb_course.databinding.ActivityDefaultPage2Binding;
import com.KKS.ukrtb_course.databinding.ActivityDefaultPage3Binding;

public class DefaultPage3 extends AppCompatActivity {

    private ActivityDefaultPage3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDefaultPage3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
    public void homeOnClick(View view){
        startActivity(new Intent(DefaultPage3.this, DefaultCourseBlockOne.class));
        finish();
    }
}