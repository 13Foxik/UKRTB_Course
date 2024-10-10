package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import java.util.Random;
import com.bumptech.glide.Glide;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

public class LoadingScreen extends AppCompatActivity {
    private final int freezeTime = 1000;

    private final int[] gifArray = {
            R.drawable.birdgif,   //Массив гифок
            R.drawable.beargif,
            R.drawable.busycat,
            R.drawable.pingvigif
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        RandomizeGifs();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run(){

                //Проверка -> авторизировался ли уже пользователь на устройстве
                if(FirebaseAuth.getInstance().getCurrentUser() == null){
                    startActivity(new Intent(LoadingScreen.this, MainActivity.class));
                }
                else{
                    startActivity(new Intent(LoadingScreen.this, Profile.class));
                }
            }
        }, freezeTime);
    }

    private void RandomizeGifs(){
        Random random = new Random();
        ImageView GifImage = findViewById(R.id.GifImage);
        int randomGifIndex = random.nextInt(gifArray.length);

        Glide.with(this)
                .asGif()
                .load(gifArray[randomGifIndex])
                .into(GifImage);
    }

}