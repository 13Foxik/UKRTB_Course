package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CheckBox CheckPassword;
    private EditText PasswordText;
    private EditText LoginText;
    private ImageButton transitionToReg;
    private ImageButton transitionToSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init(); // Создал отдельный метод для иницилиазиции переменных 52 строчка(йоу)

        CheckPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckPasswordFunction();
            }
        });

        transitionToReg.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v) {
                Intent regActivity = new Intent(MainActivity.this, registration.class);
                MainActivity.this.startActivity(regActivity);
                MainActivity.this.finish();
            }
        });
        transitionToSign.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v) {
                Intent profActivity = new Intent(MainActivity.this, Profile.class);
                MainActivity.this.startActivity(profActivity);
                MainActivity.this.finish();
            }
        });
    }

    private void Init(){
        PasswordText = findViewById(R.id.PasswordText);
        LoginText= findViewById(R.id.LoginText);
        CheckPassword = findViewById(R.id.CheckPassword);
        transitionToReg = findViewById(R.id.ButtonReg);
        transitionToSign = findViewById(R.id.ButtonSignUp);
    }

    private void CheckPasswordFunction(){                                                           //чтобы немного раскомповать код решил создать отдельный метод который
        this.CheckPassword.setButtonDrawable(R.drawable.checkbox_selector);                         //исполняет функционал глазика

        if(CheckPassword.isChecked()){
            PasswordText.setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            PasswordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }



}