package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.KKS.ukrtb_course.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class registration extends AppCompatActivity {

    private ImageButton regButton;

    private ActivityRegistrationBinding binding;

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Validation()){
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.edLogin.getText().toString(), binding.edPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){

                                        writeNewUser(FirebaseAuth.getInstance().getCurrentUser().getUid(), binding.NickName.getText().toString(), binding.edLogin.getText().toString());

                                        startActivity(new Intent(registration.this, Profile.class));
                                    }
                                }
                            });
                }
            }
        });
    }

    private void init(){
        regButton = findViewById(R.id.RegButton);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private boolean Validation(){
        boolean result = false;
        if(binding.edLogin.getText().toString().isEmpty() || binding.edPassword.getText().toString().isEmpty() || binding.NickName.getText().toString().isEmpty() || binding.edSuccsesPassword.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Не все обязательные поля были заполнены", Toast.LENGTH_SHORT).show();
        }
        else if(binding.edPassword.getText().toString().length() < 6) {
            Toast.makeText( getApplicationContext(), "Пароль не может быть меньше 6 символов", Toast.LENGTH_SHORT ).show();
        }
        else if(!binding.edSuccsesPassword.getText().toString().equals( binding.edPassword.getText().toString())){
            Toast.makeText( getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_SHORT ).show();
        }
        else if(!binding.edLogin.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+[.][a-z]+")){
            Toast.makeText( getApplicationContext(), "Неправильный формат E-mail", Toast.LENGTH_SHORT ).show();
        }
        else if(!binding.edPhone.getText().toString().matches("^[+7][0-9]") && binding.edPhone.getText().toString().length() != 12){
            Toast.makeText( getApplicationContext(), "Неправильно набран номер", Toast.LENGTH_SHORT ).show();
        }
        else if(binding.NickName.getText().toString().matches("[а-яА-Яa-zA-Z0-9]")){
            Toast.makeText( getApplicationContext(), "В никнейме могут содержаться только буквы, цифры, и _", Toast.LENGTH_SHORT ).show();
        }
        else{
            result = true;
        }

        return result;
    }

    public void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email, "");

        mDatabase.child("Users").child(userId).setValue(user);
    }
}