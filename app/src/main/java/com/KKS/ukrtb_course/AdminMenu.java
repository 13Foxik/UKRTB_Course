package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

public class AdminMenu extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityAdminMenuBinding binding;
    private DatabaseReference mDataBase;
    private DatabaseReference uidRef;
    private String uid;
    private String emailText;
    private Spinner spinner;
    private String selectCourse;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminMenuBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_admin_menu);
        init();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.spinner){
            selectCourse = parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
        spinner = findViewById((R.id.spinner));

        spinner.setOnItemSelectedListener(this);

        String[] coursesName = getResources().getStringArray(R.array.nameCourses);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, coursesName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setAccessCourse(String nameCourse, String UserID, Boolean accept) {
        // Получаем ссылку на пользователя по UserID
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(UserID);

        ValueEventListener imageListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User user = dataSnapshot.getValue(User.class);
                    if (user.accessOfCourses == null) {
                        user.accessOfCourses = new ArrayList<String>(); // Инициализация списка, если он null
                    }

                    // Логика добавления или удаления курса
                    if (accept){
                        if (user.accessOfCourses.contains(nameCourse)) {
                            Toast.makeText(AdminMenu.this,
                                    "Данному пользователю уже выдан доступ к " + nameCourse,
                                    Toast.LENGTH_LONG).show();
                        }else{
                            user.accessOfCourses.add(nameCourse);
                        }
                    } else{
                        if (!user.accessOfCourses.contains(nameCourse)) {
                            Toast.makeText(AdminMenu.this,
                                    "У данного пользователя уже ограничен доступ к " + nameCourse,
                                    Toast.LENGTH_LONG).show();
                        }else{
                            user.accessOfCourses.remove(nameCourse);
                        }
                    }

                    // Обновляем данные пользователя в Firebase
                    userRef.setValue(user).addOnCompleteListener(task -> {
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

        Log.e("TAG", "Current User UID: " + UserID);
        userRef.addListenerForSingleValueEvent(imageListener); // Используем addListenerForSingleValueEvent
    }

    // Интерфейс обратного вызова
    public interface UidCallback {
        void onCallback(String uid);
    }

    // Измененный метод findUidByEmail
    public String findUidByEmail(String email ,UidCallback callback) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Users");

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean userFound = false;
                String foundUid = null; // Переменная для хранения найденного UID
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String userEmail = userSnapshot.child("email").getValue(String.class);
                    if (userEmail != null && userEmail.equals(email)) {
                        foundUid = userSnapshot.getKey(); // UID документа
                        userFound = true;
                        break; // Выход из цикла, если пользователь найден
                    }
                }
                if (userFound) {
                    callback.onCallback(foundUid); // Передаем найденный UID через callback
                } else {
                    Log.d("Firebase", "Пользователь не найден");
                    callback.onCallback(null); // Передаем null, если пользователь не найден
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "Ошибка получения UID", databaseError.toException());
                callback.onCallback(null); // Передаем null в случае ошибки
            }
        });
        return email;
    }

    public void OnClickBack(View view){
        startActivity(new Intent(AdminMenu.this, Profile.class));
        finish();
    }

    public void AcceptSettings(Boolean accept){
        EditText emailEditText = findViewById(R.id.email);
        String emailText = emailEditText.getText().toString(); // Исправлено на String
        findUidByEmail(emailText, new UidCallback() {
            @Override
            public void onCallback(String uid) {
                if (uid != null) {
                    // UID найден, теперь можно вызывать setAccessCourse
                    Log.d("Firebase", "Найденный UID: " + uid);
                    setAccessCourse(selectCourse, uid, accept); // Передаем найденный UID в метод
                } else {
                    // UID не найден или произошла ошибка
                    Log.d("Firebase", "UID не найден или произошла ошибка");
                }
            }
        });
    }

    public void OnClickGiveAccept(View view) {
        AcceptSettings(true);
    }
    public void OnClickRemoveAccept(View view) {
        AcceptSettings(false);
    }

}