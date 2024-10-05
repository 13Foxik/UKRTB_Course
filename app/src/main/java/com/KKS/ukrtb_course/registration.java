package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class registration extends AppCompatActivity {

    private ImageButton regButton;

    private EditText edLogin, edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        init();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent signUpActivity = new Intent(registration.this, MainActivity.class);
//                registration.this.startActivity(signUpActivity);
//                registration.this.finish();



            }
        });
    }
    private void init(){
        regButton = findViewById(R.id.RegButton);
        edLogin = findViewById(R.id.edLogin);
        edPassword = findViewById(R.id.edPassword);
    }
}