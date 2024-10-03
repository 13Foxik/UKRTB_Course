package com.KKS.ukrtb_course;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Gainmap;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import java.util.Random;
import com.bumptech.glide.Glide;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoadingScreen extends AppCompatActivity {
    private int[] gifArray = {
            R.drawable.birdgif,   //Массив гифок
            R.drawable.beargif,
            R.drawable.busycat,
            R.drawable.pingvigif
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        ImageView imageview = findViewById(R.id.imageview); //пока не разобрался как работают нижние 7 строчек, впринципе похуй главное что работает
        Random random = new Random();
        int randomGifIndex = random.nextInt(gifArray.length);
        Glide.with(this)
                .asGif()
                .load(gifArray[randomGifIndex])
                .into(imageview);
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