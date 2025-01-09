package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.KKS.ukrtb_course.databinding.ActivityAdminMenuBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminMenu extends AppCompatActivity {

    private ActivityAdminMenuBinding binding;
    private DatabaseReference mDataBase;
    private DatabaseReference uidRef;
    private String uid;
    private String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminMenuBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_admin_menu);
        init();
    }

    private void init(){
        mDataBase = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            uid = auth.getCurrentUser().getUid();
        } else {
            Log.e("TAG", "User not authenticated");
            // Здесь можно перенаправить пользователя на экран входа
            startActivity(new Intent(AdminMenu.this, MainActivity.class));
            finish(); // Закрыть текущую активность
            return;
        }
        uidRef = mDataBase.child("Users/").child(uid);
    }

    private void setAccessCourse(String nameCourse) {
        ValueEventListener imageListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User user = dataSnapshot.getValue(User.class);
                    if (user.accessOfCourses == null) {
                        user.accessOfCourses = new ArrayList<String>(); // Инициализация списка, если он null
                    }

                    // Логика добавления или удаления "Unity"
                    if (user.accessOfCourses.contains(nameCourse)) {
                        user.accessOfCourses.remove(nameCourse);
                    } else {
                        user.accessOfCourses.add(nameCourse);
                    }

                    // Обновляем данные пользователя в Firebase
                    uidRef.setValue(user).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "User updated successfully");
                        } else {
                            Log.e("TAG", "User update failed: " + task.getException().getMessage());
                        }
                    });

                } else {
                    Log.e("TAG", "Data does not exist");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "Database error: " + databaseError.getMessage());
            }
        };

        Log.e("TAG", "Current User UID: " + uid);
        uidRef.addListenerForSingleValueEvent(imageListener); // Используем addListenerForSingleValueEvent
    }

    public void findUidByEmail(String email) {
        Toast.makeText(AdminMenu.this,
                email, Toast.LENGTH_SHORT).show();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Users");

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean userFound = false;
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String userEmail = userSnapshot.child("email").getValue(String.class);
                    if (userEmail != null && userEmail.equals(email)) {
                        String uid = userSnapshot.getKey(); // UID документа
                        Toast.makeText(AdminMenu.this,
                                "Uid пользователя: " + uid, Toast.LENGTH_SHORT).show();
                        userFound = true;
                        break; // Выход из цикла, если пользователь найден
                    }
                }
                if (!userFound) {
                    Log.d("Firebase", "Пользователь не найден");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "Ошибка получения UID", databaseError.toException());
            }
        });
    }

    public void OnClickFind(View view){
        EditText emailEditText = findViewById(R.id.email);
        emailText = emailEditText.getText().toString();
        findUidByEmail(emailText);
    }

    public void OnClickBack(View view){
        startActivity(new Intent(AdminMenu.this, Profile.class));
        finish();
    }
    public void OnClickUnity(View view){
        setAccessCourse("Unity");
    }
    public void OnClick1S(View view){
        setAccessCourse("1S");
    }
    public void OnClickRobot(View view){
        setAccessCourse("Robot");
    }
    public void OnClickDron(View view){
        setAccessCourse("Dron");
    }
}