package com.example.carshowroom;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MapWebViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_map_web_view);



        // Initialize WebView
        WebView webView = findViewById(R.id.webView);

        // Clear WebView cache and disable cache mode
        webView.clearCache(true);
        // Disable cache
        webView.getSettings().setCacheMode(android.webkit.WebSettings.LOAD_NO_CACHE);

        // Enable JavaScript for loading Google Maps
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setGeolocationEnabled(true); // Enable geolocation
        // Set WebViewClient to override the default behavior of opening external browsers
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true; // Stay in WebView
            }


            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                // Handle WebView error
                Toast.makeText(MapWebViewActivity.this, "Error loading map: " + description, Toast.LENGTH_SHORT).show();
            }
        });

        // Get the location query from the Intent
        String locationQuery = getIntent().getStringExtra("locationQuery");
        if (locationQuery != null) {
            // Construct Google Maps search URL
            String url = "https://www.google.com/maps/search/" + locationQuery;
            webView.loadUrl(url);
        } else {
            Toast.makeText(this, "No location found", Toast.LENGTH_SHORT).show();
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Assuming you have dealership markers on your Google Maps
    }

}