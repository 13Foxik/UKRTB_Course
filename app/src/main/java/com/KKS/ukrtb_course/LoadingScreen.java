package com.KKS.ukrtb_course;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Gainmap;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        int freezeTime = 1000;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run(){
                Intent LoadingScreen = new Intent(LoadingScreen.this, MainActivity.class);
                LoadingScreen.this.startActivity(LoadingScreen);
                LoadingScreen.this.finish();
            }
        }, freezeTime);
    }

}