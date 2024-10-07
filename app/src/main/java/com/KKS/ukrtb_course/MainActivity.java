package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.KKS.ukrtb_course.databinding.ActivityMainBinding;
import com.KKS.ukrtb_course.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private CheckBox CheckPassword;
    private EditText PasswordText;
    private EditText LoginText;
    private ImageButton registrationBtn;
    private ImageButton loginBtn;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Init(); // Создал отдельный метод для иницилиазиции переменных

        CheckPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckPasswordFunction();
            }
        });

        registrationBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v) {
                Intent regActivity = new Intent(MainActivity.this, registration.class);
                MainActivity.this.startActivity(regActivity);
                MainActivity.this.finish();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v) {
                if(binding.LoginText.getText().toString().isEmpty() || binding.PasswordText.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fields cannot be  empty", Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.LoginText.getText().toString(), binding.PasswordText.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        startActivity(new Intent(MainActivity.this, Profile.class));
                                    }
                                }
                            });
                }
            }
        });
    }

    private void Init(){
        PasswordText = findViewById(R.id.PasswordText);
        LoginText= findViewById(R.id.LoginText);
        CheckPassword = findViewById(R.id.CheckPassword);
        registrationBtn = findViewById(R.id.ButtonReg);
        loginBtn = findViewById(R.id.ButtonSignUp);
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