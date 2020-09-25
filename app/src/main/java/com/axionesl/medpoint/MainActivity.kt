package com.axionesl.medpoint

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import im.delight.android.webview.AdvancedWebView


class MainActivity : AppCompatActivity(), AdvancedWebView.Listener {
    private lateinit var advancedWebView: AdvancedWebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermissions()
        advancedWebView = findViewById(R.id.web_view)
        setupWebView()
        advancedWebView.setListener(this, this)
        advancedWebView.loadUrl("https://medpoint.now.sh/")
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        val settings = advancedWebView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.pluginState = WebSettings.PluginState.ON
        settings.mediaPlaybackRequiresUserGesture = false
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.builtInZoomControls = true
        // Hide the zoom controls for HONEYCOMB+
        settings.displayZoomControls = false;
        val cookieManager: CookieManager = CookieManager.getInstance()
        cookieManager.setAcceptThirdPartyCookies(advancedWebView, true)
        advancedWebView.webChromeClient = object : WebChromeClient() {
            // Need to accept permissions to use the camera
            override fun onPermissionRequest(request: PermissionRequest) {
                request.grant(request.resources)
            }
        }
    }

    @Suppress("LocalVariableName")
    private fun checkPermissions() {
        val PERMISSION_ALL = 1
        val PERMISSIONS = arrayOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.MODIFY_AUDIO_SETTINGS
        )

        if (!hasPermissions(this, *PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL)
        }
    }

    override fun onPageFinished(url: String?) {
    }

    override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {
    }

    override fun onDownloadRequested(
        url: String?,
        suggestedFilename: String?,
        mimeType: String?,
        contentLength: Long,
        contentDisposition: String?,
        userAgent: String?
    ) {
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

    private fun hasPermissions(context: Context, vararg permissions: String): Boolean =
        permissions.all {
            ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
}