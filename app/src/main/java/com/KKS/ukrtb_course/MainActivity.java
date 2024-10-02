package com.KKS.ukrtb_course;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private CheckBox CheckPassword;
    private EditText PasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PasswordText = findViewById(R.id.PasswordText);
        CheckPassword = findViewById(R.id.CheckPassword);

        CheckPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckPasswordFunction(CheckPassword, PasswordText);
            }
        });
    }
    private void CheckPasswordFunction(CheckBox CheckPassword, EditText PasswordText){              //чтобы немного раскомповать код решил создать отдельный метод который
        this.CheckPassword.setButtonDrawable(R.drawable.checkbox_selector);                         //исполняет функционал глазика

        if(CheckPassword.isChecked()){
            PasswordText.setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            PasswordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }



}