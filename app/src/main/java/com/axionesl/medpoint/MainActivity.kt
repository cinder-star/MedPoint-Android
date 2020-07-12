package com.axionesl.medpoint

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import im.delight.android.webview.AdvancedWebView


class MainActivity : AppCompatActivity(), AdvancedWebView.Listener {
    private lateinit var advancedWebView: AdvancedWebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        advancedWebView = findViewById(R.id.web_view)
        advancedWebView.setListener(this, this)
        advancedWebView.loadUrl("https://medpoint.now.sh/")
    }

    override fun onPageFinished(url: String?) {
    }

    override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {
    }

    override fun onDownloadRequested(url: String?, suggestedFilename: String?, mimeType: String?, contentLength: Long, contentDisposition: String?, userAgent: String?) {
    }

    override fun onExternalPageRequest(url: String?) {
    }

    override fun onPageStarted(url: String?, favicon: Bitmap?) {
    }

    override fun onBackPressed() {
        if (!advancedWebView.onBackPressed()) {
            return
        }
        super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        advancedWebView.onActivityResult(requestCode, resultCode, intent)
    }

    override fun onDestroy() {
        advancedWebView.onDestroy()
        super.onDestroy()
    }

    @SuppressLint("NewApi")
    override fun onPause() {
        advancedWebView.onPause()
        super.onPause()
    }

    @SuppressLint("NewApi")
    override fun onResume() {
        super.onResume()
        advancedWebView.onResume()
    }
}