package com.KKS.ukrtb_course;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.Calendar;

public class Profile extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private EditText editTextBirthday;
    private ImageView imageViewAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        EditText editTextFullName = findViewById(R.id.TextFullName);
        editTextBirthday = findViewById(R.id.TextBirthday);
        imageViewAvatar = findViewById(R.id.imageViewAvatar);

        // календарь для выбора даты
        editTextBirthday.setOnClickListener(v -> showDatePickerDialog());

        // для смены аватарки нужно тыкнуть на аватарку
        imageViewAvatar.setOnClickListener(v -> selectImageFromGallery());
    }

    // отображение календаря и выбор даты
    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
                    editTextBirthday.setText(selectedDate);
                }, year, month, day);
        datePickerDialog.show();
    }

    // выбор изобржаения из галереи
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
}
