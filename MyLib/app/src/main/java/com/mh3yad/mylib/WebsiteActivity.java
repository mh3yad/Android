package com.mh3yad.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class WebsiteActivity extends AppCompatActivity {

    private WebView webView;
    private EditText websiteLink;
    private Button btnLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);
        overridePendingTransition(R.anim.slide_in,R.anim.sile_out);
        websiteLink = findViewById(R.id.websiteLink);
        webView = findViewById(R.id.webView);
        btnLink = findViewById(R.id.btnLink);
        websiteLink.setText("mh3yad.github.io/infosec/");
        webView.loadUrl("https://mh3yad.github.io/infosec/");
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("https://www."+websiteLink.getText().toString());
                webView.setWebViewClient(new WebViewClient());
                webView.getSettings().setJavaScriptEnabled(true);
            }
        });



    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }

    }

}