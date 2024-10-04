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
    private ImageButton transitionToReg;
    private ImageButton transitionToSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PasswordText = findViewById(R.id.PasswordText);
        CheckPassword = findViewById(R.id.CheckPassword);
        transitionToReg = findViewById(R.id.ButtonReg);
        transitionToSign = findViewById(R.id.ButtonSignUp);

        CheckPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckPasswordFunction(CheckPassword, PasswordText);
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
    private void CheckPasswordFunction(CheckBox CheckPassword, EditText PasswordText){              //чтобы немного раскомповать код решил создать отдельный метод который
        this.CheckPassword.setButtonDrawable(R.drawable.checkbox_selector);                         //исполняет функционал глазика

        if(CheckPassword.isChecked()){
            PasswordText.setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            PasswordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }



}