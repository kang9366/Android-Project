package com.example.webbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    private val webView: WebView by lazy{
        findViewById(R.id.webView)
    }
    private val addressBar: EditText by lazy {
        findViewById(R.id.addressBar)
    }
    private val homeButton: ImageButton by lazy {
        findViewById(R.id.homeButton)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        homeButton.setOnClickListener {
            webView.loadUrl("${addressBar.text}")
        }
    }

    private fun initViews(){
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://www.google.com")
    }
}