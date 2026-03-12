# OpenClaw Android Chat App - NO MEMORY VERSION

**Version:** 1.0.0  
**Target:** Android 8.0+ (API 26+)  
**Purpose:** Lightweight chat app - No local storage

---

## 🚫 NO LOCAL STORAGE

**By design, this app does NOT:**
- ❌ Store messages locally
- ❌ Use Room database
- ❌ Persist chat history
- ❌ Save user data on device

**All messages exist only in memory and are lost when app closes.**

---

## 📱 Features

- ✅ Real-time chat with OpenClaw
- ✅ Material Design 3 UI
- ✅ Dark/Light theme
- ✅ WebSocket support
- ✅ Token-based authentication
- ✅ Lightweight (~15MB APK)
- ✅ No database overhead

---

## 🏗️ Tech Stack

| Component | Technology |
|-----------|------------|
| Language | Kotlin |
| UI | Jetpack Compose |
| Architecture | MVVM |
| Networking | OkHttp + WebSocket |
| DI | Hilt |
| Storage | **NONE** (in-memory only) |
| Min SDK | 26 (Android 8.0) |
| Target SDK | 34 (Android 14) |

---

## 🚀 Quick Build Guide

### Step 1: Open in Android Studio
```
File → Open → Select openclaw-android-app folder
```

### Step 2: Update Config.kt
Edit `app/src/main/java/com/openclaw/chat/Config.kt`:
```kotlin
const val OPENCLAW_HOST = "192.168.1.XXX" // Your PC's IP
const val API_TOKEN = "your-token" // From OpenClaw Control UI
```

### Step 3: Build APK
```
Build → Build Bundle(s) / APK(s) → Build APK(s)
```

### Step 4: Install
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

---

## 📁 Simplified Structure

```
app/
├── src/main/
│   ├── java/com/openclaw/chat/
│   │   ├── MainActivity.kt
│   │   ├── OpenClawApp.kt
│   │   ├── Config.kt
│   │   ├── ui/
│   │   │   ├── ChatScreen.kt
│   │   │   ├── ChatViewModel.kt (NO DATABASE)
│   │   │   └── theme/
│   │   └── domain/model/
│   │       └── Message.kt
│   └── res/
└── build.gradle.kts
```

**Removed:**
- ❌ data/local/ (Room database)
- ❌ di/DatabaseModule.kt
- ❌ MessageDao.kt
- ❌ MessageEntity.kt
- ❌ ChatDatabase.kt

---

## ⚡ Benefits of No-Memory Design

1. **Simpler Code** - No database boilerplate
2. **Smaller APK** - ~5MB smaller without Room
3. **Faster Build** - No KSP/Room compilation
4. **Better Privacy** - No data stored on device
5. **Less Maintenance** - No migrations, no schema updates

---

## 🔒 Privacy & Security

**Data Handling:**
- Messages exist only in RAM
- Cleared when app closes
- No local database to extract
- No forensic traces

**Network Security:**
- Token-based authentication
- Configurable network security
- Support for HTTPS (recommended for production)

---

## 📝 Notes

**This design is intentional because:**
1. OpenClaw Gateway already stores conversation history
2. No need to duplicate storage on client
3. Reduces app complexity
4. Better for privacy-conscious users

**If you need offline support:**
- Messages will be lost when app closes
- Reconnect to OpenClaw to retrieve history from Gateway
- Consider enabling Gateway-side message persistence

---

**Last Updated:** 2026-03-12  
**Version:** 1.0.0 (No-Memory Edition)  
**Build:** 1
