package com.KKS.ukrtb_course;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;                                                                          // вот эта часть выше я не знаю что это за хуйня честно, оно само инстантли
                                                                                                    //была написана
        });

        PasswordText = findViewById(R.id.PasswordText);
        CheckPassword = findViewById(R.id.CheckPassword);

        CheckPassword.setOnClickListener(new View.OnClickListener() {                               // Эта строчка нужна для того чтобы работал метод onclick для чекбокса
            @Override
            public void onClick(View v) {

                CheckPassword.setButtonDrawable(R.drawable.checkbox_selector);                      // Меняет картинку глазика, он там к xml файлу конектит и в самом xml
                                                                                                    // когда true одна картинка, false другая

                if(CheckPassword.isChecked()){                                                      // проверка на чекбокс
                    PasswordText.setInputType(InputType.TYPE_CLASS_TEXT);                           // Присваивает тип выводящих данных
                } else {
                    PasswordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }



}