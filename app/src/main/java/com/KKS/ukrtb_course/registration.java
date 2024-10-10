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
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class registration extends AppCompatActivity {

    private ImageButton regButton;

    private ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.edLogin.getText().toString().isEmpty() || binding.edPassword.getText().toString().isEmpty() || binding.NickName.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fields cannot be  empty", Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.edLogin.getText().toString(), binding.edPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(binding.edSuccsesPassword.getText().toString().equals( binding.edPassword.getText().toString() ))
                                    {
                                        if(task.isSuccessful()){
                                            HashMap<String, String> userInfo = new HashMap<>();
                                            userInfo.put("email", binding.edLogin.getText().toString());
                                            userInfo.put("username", binding.NickName.getText().toString());
                                            FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .setValue(userInfo);

                                            startActivity(new Intent(registration.this, Profile.class));
                                        }
                                        else if(binding.edPassword.getText().toString().length() < 6) {
                                            Toast.makeText( getApplicationContext(), "Пароль не может быть меньше 6 символов", Toast.LENGTH_SHORT ).show();
                                        }
                                    }
                                    else {
                                        Toast.makeText( getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_SHORT ).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void init(){
        regButton = findViewById(R.id.RegButton);
    }
}