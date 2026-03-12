package com.openclaw.chat

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for OpenClaw Chat
 * Initializes Hilt dependency injection
 */
@HiltAndroidApp
class OpenClawApp : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        if (Config.DEBUG_LOGGING) {
            Log.d("OpenClawApp", "Application initialized")
            Log.d("OpenClawApp", "OpenClaw Host: ${Config.OPENCLAW_HOST}")
            Log.d("OpenClawApp", "OpenClaw Port: ${Config.OPENCLAW_PORT}")
        }
        
        // Validate configuration on startup
        if (!Config.isValid()) {
            Log.e("OpenClawApp", "⚠️ Configuration is invalid! App may not work properly.")
            Log.e("OpenClawApp", "Please update Config.kt with your OpenClaw details.")
        }
    }
}
