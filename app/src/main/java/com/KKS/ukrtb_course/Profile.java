package com.KKS.ukrtb_course;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.Calendar;

public class Profile extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private ImageView imageViewAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Init();

        // для смены аватарки нужно тыкнуть на аватарку
        imageViewAvatar.setOnClickListener(v -> selectImageFromGallery());
    }

    private void Init(){
        imageViewAvatar = findViewById(R.id.imageViewAvatar);
    }

    // выбор изобржаения из галереи
    @SuppressLint("IntentReset")
    private void selectImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }

    // вроде для обработки изображения или чет такое
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imageViewAvatar.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onClickLogout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(Profile.this, MainActivity.class));
    }
}
