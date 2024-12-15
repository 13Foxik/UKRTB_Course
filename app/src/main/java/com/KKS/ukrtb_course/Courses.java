package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.KKS.ukrtb_course.databinding.ActivityCoursesBinding;
import com.KKS.ukrtb_course.databinding.ActivityProfileBinding;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Courses extends AppCompatActivity {

    private ActivityCoursesBinding binding;
    private DatabaseReference mDataBase;
    private DatabaseReference uidRef;
    private String uid;
    private ImageButton myImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCoursesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseApp.initializeApp(this);
        mDataBase = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();
        uidRef = mDataBase.child("Users/").child(uid);
        drawImage();
    }
    private void CheckAccess(String nameCourse, AccessCallback callback) {
        uidRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean hasAccess = false;
                if (dataSnapshot.exists()) {
                    User user = dataSnapshot.getValue(User.class);
                    if (user.accessOfCourses.contains(nameCourse)) {
                        hasAccess = true;
                        Log.e("TAG", "Доступ есть");
                    } else {
                        Log.e("TAG", "Доступа нет");
                    }
                } else {
                    Log.e("TAG", "Data does not exist");
                }
                callback.onAccessChecked(hasAccess);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "Database error: " + databaseError.getMessage());
                callback.onAccessChecked(false); // или обработка ошибки
            }
        });
    }

    // Интерфейс для коллбека
    public interface AccessCallback {
        void onAccessChecked(boolean hasAccess);
    }
    private void drawImage(){
        //пиздец какой то а не метод........ я просто уже хз как это оптимизировать
        String[] courses = {"Unity", "1S", "Robot", "Dron"};
        int[] buttons = {R.id.UnityBtn, R.id.OnesBtn, R.id.RobotBtn, R.id.DronsBtn};
        int[] images = {R.drawable.unity, R.drawable.ones, R.drawable.robotechnology, R.drawable.drons};

        for (int i = 0; i < courses.length; i++) {
            final String course = courses[i];
            final int buttonId = buttons[i];
            final int imageResId = images[i];

            CheckAccess(course, new AccessCallback() {
                @Override
                public void onAccessChecked(boolean hasAccess) {
                    if (hasAccess) {
                        ImageButton myImageButton = findViewById(buttonId);
                        myImageButton.setImageResource(imageResId);
                    }
                }
            });
        }
    }
    public void unityOnClick(View view) {
        CheckAccess("Unity", new AccessCallback() {
            @Override
            public void onAccessChecked(boolean hasAccess) {
                if (hasAccess) {
                    startActivity(new Intent(Courses.this, DefalutCourse.class));
                    finish();
                }
            }
        });
    }

    public void onesOnClick(View view) {
        CheckAccess("1S", new AccessCallback() {
            @Override
            public void onAccessChecked(boolean hasAccess) {
                if (hasAccess) {
                    startActivity(new Intent(Courses.this, DefalutCourse.class));
                    finish();
                }
            }
        });
    }

    public void robotOnClick(View view) {
        CheckAccess("Robot", new AccessCallback() {
            @Override
            public void onAccessChecked(boolean hasAccess) {
                if (hasAccess) {
                    startActivity(new Intent(Courses.this, DefalutCourse.class));
                    finish();
                }
            }
        });
    }

    public void dronOnClick(View view) {
        CheckAccess("Dron", new AccessCallback() {
            @Override
            public void onAccessChecked(boolean hasAccess) {
                if (hasAccess) {
                    startActivity(new Intent(Courses.this, DefalutCourse.class));
                    finish();
                }
            }
        });
    }

    public void profileOnClick(View view) {
        startActivity(new Intent(Courses.this, Profile.class));
        finish();
    }
}