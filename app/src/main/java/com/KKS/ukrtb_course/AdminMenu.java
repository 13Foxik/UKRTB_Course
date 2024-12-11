package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.KKS.ukrtb_course.databinding.ActivityAdminMenuBinding;
import com.KKS.ukrtb_course.databinding.ActivityCoursesBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdminMenu extends AppCompatActivity {

    private ActivityAdminMenuBinding binding;
    private DatabaseReference mDataBase;
    private FirebaseAuth mAuth;
    private DatabaseReference uidRef;
    private String uid;

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

    private void setDates() {
        ValueEventListener imageListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User user = dataSnapshot.getValue(User.class);
                    if (user.accessOfCourses == null) {
                        user.accessOfCourses = new ArrayList<String>(); // Инициализация списка, если он null
                    }

                    // Логика добавления или удаления "Unity"
                    if (user.accessOfCourses.contains("Unity")) {
                        user.accessOfCourses.remove("Unity");
                    } else {
                        user.accessOfCourses.add("Unity");
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

    public void OnClickBack(View view){
        startActivity(new Intent(AdminMenu.this, Profile.class));
        finish();
    }
    public void OnClickUnity(View view){
        setDates();
    }
}