package com.KKS.ukrtb_course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.KKS.ukrtb_course.databinding.ActivityUnityB1S2P1Binding;
import com.KKS.ukrtb_course.databinding.ActivityUnityB1S2P2Binding;
import com.KKS.ukrtb_course.databinding.ActivityUnityB1S3P1Binding;

public class UnityB1S3P1 extends AppCompatActivity {

    private ActivityUnityB1S3P1Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUnityB1S3P1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        WebView webView = findViewById(R.id.WebView);
        String video = "<html><body style=\"margin:0;padding:0;background-color:transparent;\"><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/w8rRhAup4kg?si=c5BrNCnLWpSJqYri&amp;start=6187\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe></body></html>";
        webView.loadData(video, "text/html", "utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
    }
    public void homeOnClick(View view ){
        startActivity(new Intent(UnityB1S3P1.this, UnityB1.class));
        finish();
    }
}