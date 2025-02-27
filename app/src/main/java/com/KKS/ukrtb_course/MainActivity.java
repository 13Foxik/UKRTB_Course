package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.KKS.ukrtb_course.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private CheckBox CheckPassword;
    private EditText PasswordText;
    private Button registrationBtn;
    private Button loginBtn;
    private ActivityMainBinding binding;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Init(); // Создал отдельный метод для инициализации переменных
        mAuth = FirebaseAuth.getInstance();  // Инициализация FirebaseAuth

        CheckPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckPasswordFunction();
            }
        });

        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regActivity = new Intent(MainActivity.this, registration.class);
                MainActivity.this.startActivity(regActivity);
                MainActivity.this.finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.LoginText.getText().toString().isEmpty() || binding.PasswordText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show();
                } else {
                    // Попытка входа пользователя
                    mAuth.signInWithEmailAndPassword(binding.LoginText.getText().toString(), binding.PasswordText.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if (user != null) {
                                            // Проверяем, подтвержден ли email
                                            if (user.isEmailVerified()) {
                                                // Если email подтвержден, переходим к профилю
                                                startActivity(new Intent(MainActivity.this, Profile.class));
                                                finish();  // Закрыть MainActivity
                                            } else {
                                                // Если email не подтвержден, разлогинить пользователя и показать сообщение
                                                mAuth.signOut();
                                                Toast.makeText(MainActivity.this,
                                                        "Подтвердите свой email перед входом.",
                                                        Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    } else {
                                        // Ошибка при входе (например, неверный логин или пароль)
                                        Toast.makeText(getApplicationContext(), "Неправильный логин или пароль", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void Init() {
        PasswordText = findViewById(R.id.PasswordText);
        CheckPassword = findViewById(R.id.CheckPassword);
        registrationBtn = findViewById(R.id.ButtonReg);
        loginBtn = findViewById(R.id.ButtonSignUp);
    }

    private void CheckPasswordFunction() {  // Метод для переключения видимости пароля
        this.CheckPassword.setButtonDrawable(R.drawable.checkbox_selector);

        if (CheckPassword.isChecked()) {
            PasswordText.setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            PasswordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }
}
