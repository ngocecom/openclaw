package com.openclaw.chat

import android.util.Log

/**
 * OpenClaw Chat App Configuration
 * 
 * IMPORTANT: Update these values before building!
 */
object Config {
    
    /**
     * OpenClaw Gateway Host
     * 
     * For local development: Use your PC's local IP (e.g., 192.168.1.100)
     * For production: Use your server's public IP or domain
     * 
     * To find your PC's IP:
     * - Windows: Open Command Prompt, type: ipconfig
     * - Look for "IPv4 Address" under your network adapter
     */
    const val OPENCLAW_HOST = "192.168.1.100" // CHANGE THIS!
    
    /**
     * OpenClaw Gateway Port
     * Default: 18789
     */
    const val OPENCLAW_PORT = 18789
    
    /**
     * API Token for authentication
     * 
     * Get this from your OpenClaw configuration
     * File: ~/.openclaw/openclaw.json or via Control UI
     */
    const val API_TOKEN = "your-api-token-here" // CHANGE THIS!
    
    /**
     * WebSocket endpoint path
     */
    const val WS_PATH = "/ws"
    
    /**
     * REST API endpoint path
     */
    const val API_PATH = "/api"
    
    /**
     * Connection timeout (milliseconds)
     */
    const val CONNECTION_TIMEOUT = 30000L
    
    /**
     * Message send timeout (milliseconds)
     */
    const val SEND_TIMEOUT = 10000L
    
    /**
     * Reconnect delay (milliseconds)
     */
    const val RECONNECT_DELAY = 5000L
    
    /**
     * Enable debug logging
     */
    const val DEBUG_LOGGING = true
    
    /**
     * LOCAL STORAGE ENABLED
     * Messages ARE persisted to Room database
     * Messages persist when app closes
     */
    const val PERSIST_MESSAGES = true
    
    /**
     * Get base URL for HTTP requests
     */
    fun getBaseUrl(): String {
        return "http://$OPENCLAW_HOST:$OPENCLAW_PORT"
    }
    
    /**
     * Get WebSocket URL
     */
    fun getWebSocketUrl(): String {
        return "ws://$OPENCLAW_HOST:$OPENCLAW_PORT$WS_PATH"
    }
    
    /**
     * Validate configuration
     */
    fun isValid(): Boolean {
        val valid = OPENCLAW_HOST.isNotEmpty() && 
                    OPENCLAW_HOST != "192.168.1.100" &&
                    API_TOKEN.isNotEmpty() &&
                    API_TOKEN != "your-api-token-here"
        
        if (!valid && DEBUG_LOGGING) {
            Log.e("Config", "Configuration is invalid! Please update Config.kt")
            Log.e("Config", "OPENCLAW_HOST: $OPENCLAW_HOST")
            Log.e("Config", "API_TOKEN: ${if (API_TOKEN == "your-api-token-here") "NOT SET" else "SET"}")
        }
        
        return valid
    }
}
