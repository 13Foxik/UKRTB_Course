package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.KKS.ukrtb_course.databinding.ActivityUnityB1S4P1Binding;

public class UnityB1S4P1 extends AppCompatActivity {

    private ActivityUnityB1S4P1Binding binding;
    private String answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUnityB1S4P1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    public void nextOnClick(View view){
        answer = binding.answerText.getText().toString();
        if(Integer.parseInt(answer) == 24){
            startActivity(new Intent(UnityB1S4P1.this, UnityB1S4P2.class));
            finish();
        }
        else{
            Toast.makeText(UnityB1S4P1.this,
                    "Неправильный ответ",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void homeOnClick(View view){
        startActivity(new Intent(UnityB1S4P1.this, UnityB1.class));
        finish();
    }

}