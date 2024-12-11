package com.KKS.ukrtb_course;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.KKS.ukrtb_course.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class registration extends AppCompatActivity {

    private ImageButton regButton;
    private ActivityRegistrationBinding binding;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validation()) {
                    String email = binding.edLogin.getText().toString();
                    String password = binding.edPassword.getText().toString();
                    String birthday = binding.TextBirthday.getText().toString();
                    ArrayList<String> accessOfCourses = new ArrayList<String>();
                    accessOfCourses.add("0");

                    // Создание пользователя в Firebase
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if (user != null) {
                                            // Отправка письма для подтверждения email
                                            user.sendEmailVerification()
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> verificationTask) {
                                                            if (verificationTask.isSuccessful()) {
                                                                Toast.makeText(registration.this,
                                                                        "Письмо с подтверждением отправлено на " + email,
                                                                        Toast.LENGTH_SHORT).show();

                                                                // Запись данных нового пользователя в базу данных
                                                                writeNewUser(user.getUid(), binding.NickName.getText().toString(), email, birthday, accessOfCourses);

                                                                // Разлогинить пользователя, чтобы он подтвердил email
                                                                mAuth.signOut();
                                                                // Сообщение пользователю, чтобы он подтвердил email
                                                                Toast.makeText(registration.this,
                                                                        "Пожалуйста, подтвердите свой email перед входом.",
                                                                        Toast.LENGTH_LONG).show();

                                                                // Переход на экран входа или другую активность
                                                                startActivity(new Intent(registration.this, MainActivity.class));
                                                                finish(); // Закрыть текущую активность
                                                            } else {
                                                                Toast.makeText(registration.this,
                                                                        "Ошибка при отправке письма с подтверждением.",
                                                                        Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                        }
                                    } else {
                                        // Ошибка при регистрации
                                        Toast.makeText(registration.this,
                                                "Ошибка регистрации: " + task.getException().getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void init() {
        regButton = findViewById(R.id.RegButton);
        mAuth = FirebaseAuth.getInstance();  // Инициализация FirebaseAuth
        mDatabase = FirebaseDatabase.getInstance().getReference();  // Ссылка на базу данных Firebase
    }

    private boolean Validation() {
        boolean result = false;
        if (binding.edLogin.getText().toString().isEmpty() ||
                binding.edPassword.getText().toString().isEmpty() ||
                binding.NickName.getText().toString().isEmpty() ||
                binding.edSuccsesPassword.getText().toString().isEmpty() ||
                binding.TextBirthday.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Не все обязательные поля были заполнены", Toast.LENGTH_SHORT).show();
        } else if (binding.edPassword.getText().toString().length() < 6) {
            Toast.makeText(getApplicationContext(), "Пароль не может быть меньше 6 символов", Toast.LENGTH_SHORT).show();
        } else if (!binding.edSuccsesPassword.getText().toString().equals(binding.edPassword.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show();
        } else if (!binding.edLogin.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+[.][a-z]+")) {
            Toast.makeText(getApplicationContext(), "Неправильный формат E-mail", Toast.LENGTH_SHORT).show();
        } else if (!binding.NickName.getText().toString().matches("[а-яА-Яa-zA-Z0-9]+")) {
            Toast.makeText(getApplicationContext(), "В никнейме могут содержаться только буквы, цифры, и _", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;
    }

    public void writeNewUser(String userId, String name, String email, String birthday, ArrayList<String> accessOfCourses) {
        User user = new User(name, email, "", birthday, accessOfCourses);

        mDatabase.child("Users").child(userId).setValue(user);
    }
    public void BirthdayOnClick(View vieww){
        // календарь для выбора даты
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
                    binding.TextBirthday.setText(selectedDate);
                }, year, month, day);
        datePickerDialog.show();
    }
}
