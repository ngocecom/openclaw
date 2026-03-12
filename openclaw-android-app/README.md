# OpenClaw Android Chat App

**Version:** 1.0.0  
**Target:** Android 8.0+ (API 26+)  
**Purpose:** Native Android chat app for OpenClaw

---

## 📱 Features

- ✅ Real-time chat with OpenClaw
- ✅ Message history (local storage)
- ✅ Material Design 3 UI
- ✅ Dark/Light theme
- ✅ WebSocket support
- ✅ Token-based authentication
- ✅ Offline message queue
- ✅ Push notifications support

---

## 🏗️ Tech Stack

| Component | Technology |
|-----------|------------|
| Language | Kotlin |
| UI | Jetpack Compose |
| Architecture | MVVM |
| Networking | OkHttp + WebSocket |
| Local DB | Room |
| DI | Hilt |
| Min SDK | 26 (Android 8.0) |
| Target SDK | 34 (Android 14) |

---

## 📋 Requirements

### To Build APK:

1. **Android Studio** (Latest version)
   - Download: https://developer.android.com/studio

2. **JDK 17+**
   - Usually bundled with Android Studio

3. **Android SDK**
   - SDK Platform 34
   - Build Tools 34.0.0

4. **OpenClaw Gateway**
   - Running and accessible
   - WebSocket endpoint available

---

## 🚀 Quick Start

### Step 1: Open Project
```bash
# Open Android Studio
# File → Open → Select this project folder
```

### Step 2: Sync Gradle
```bash
# Android Studio will auto-sync
# Or: File → Sync Project with Gradle Files
```

### Step 3: Configure API Endpoint
Edit `app/src/main/java/com/openclaw/chat/Config.kt`:
```kotlin
object Config {
    const val OPENCLAW_HOST = "192.168.1.100" // Your PC's IP
    const val OPENCLAW_PORT = 18789
    const val API_TOKEN = "your-token-here"
}
```

### Step 4: Build APK
```bash
# Debug APK (for testing)
./gradlew assembleDebug

# Release APK (for production)
./gradlew assembleRelease
```

### Step 5: Install on Device
```bash
# Via ADB
adb install app/build/outputs/apk/debug/app-debug.apk

# Or transfer APK to phone and install manually
```

---

## 📁 Project Structure

```
app/
├── src/main/
│   ├── java/com/openclaw/chat/
│   │   ├── MainActivity.kt              # Main chat screen
│   │   ├── OpenClawApp.kt               # Application class
│   │   ├── Config.kt                    # Configuration
│   │   ├── ui/
│   │   │   ├── theme/                   # App theme
│   │   │   ├── components/              # UI components
│   │   │   └── ChatScreen.kt            # Chat UI
│   │   ├── data/
│   │   │   ├── repository/              # Data repository
│   │   │   ├── local/                   # Local database
│   │   │   │   ├── MessageDao.kt        # DAO
│   │   │   │   ├── MessageEntity.kt     # Entity
│   │   │   │   └── ChatDatabase.kt      # Room DB
│   │   │   └── remote/                  # API client
│   │   │       ├── OpenClawApi.kt       # API interface
│   │   │       └── WebSocketService.kt  # WebSocket
│   │   └── domain/
│   │       └── model/                   # Data models
│   │           └── Message.kt           # Message model
│   ├── res/
│   │   ├── values/                      # Strings, colors, themes
│   │   ├── drawable/                    # Icons, images
│   │   └── mipmap/                      # App icons
│   └── AndroidManifest.xml              # App manifest
├── build.gradle.kts                     # App build config
└── proguard-rules.pro                   # ProGuard rules

build.gradle.kts                         # Project build config
settings.gradle.kts                      # Project settings
gradle.properties                        # Gradle properties
```

---

## 🔧 Configuration

### Network Security (for local development)

Edit `app/src/main/res/xml/network_security_config.xml`:
```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">192.168.1.0/24</domain>
        <domain includeSubdomains="true">10.0.0.0/8</domain>
    </domain-config>
</network-security-config>
```

### Permissions

In `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
```

---

## 🎨 Customization

### Change App Name
Edit `app/src/main/res/values/strings.xml`:
```xml
<string name="app_name">OpenClaw Chat</string>
```

### Change App Icon
Replace icons in `app/src/main/res/mipmap-*/`

### Change Colors
Edit `app/src/main/res/values/colors.xml`:
```xml
<color name="primary">#FF6200EE</color>
<color name="primary_variant">#FF3700B3</color>
<color name="secondary">#FF03DAC5</color>
```

---

## 🔐 Security

### API Token Storage
- Use Android Keystore for production
- Never hardcode tokens in source code
- Use environment variables or secure config

### Network Security
- Use HTTPS in production
- Configure network security config
- Implement certificate pinning (optional)

---

## 📦 Building Release APK

### Step 1: Generate Keystore
```bash
keytool -genkey -v -keystore openclaw-chat.keystore \
  -alias openclaw -keyalg RSA -keysize 2048 -validity 10000
```

### Step 2: Configure Signing
Edit `app/build.gradle.kts`:
```kotlin
android {
    signingConfigs {
        create("release") {
            storeFile = file("../openclaw-chat.keystore")
            storePassword = System.getenv("KEYSTORE_PASSWORD")
            keyAlias = "openclaw"
            keyPassword = System.getenv("KEY_PASSWORD")
        }
    }
    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            minifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
```

### Step 3: Build
```bash
./gradlew assembleRelease
```

APK will be at: `app/build/outputs/apk/release/app-release.apk`

---

## 🐛 Troubleshooting

### Build Fails
```bash
# Clean and rebuild
./gradlew clean
./gradlew build

# Invalidate caches
File → Invalidate Caches / Restart
```

### Cannot Connect to OpenClaw
- Check OpenClaw Gateway is running
- Verify IP address and port
- Check firewall settings
- Ensure same network (WiFi)

### App Crashes on Launch
- Check Logcat for errors
- Verify API endpoint configuration
- Check network permissions

---

## 📱 Testing

### On Emulator
```bash
# Start Android emulator
# Run app from Android Studio
```

### On Physical Device
```bash
# Enable Developer Options on phone
# Enable USB Debugging
# Connect via USB
# Run: adb install app-debug.apk
```

---

## 🔄 CI/CD (Optional)

### GitHub Actions
Create `.github/workflows/android.yml`:
```yaml
name: Android CI

on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'
    - name: Grant execute permission
      run: chmod +x gradlew
    - name: Build with Gradle
      run: ./gradlew build
    - name: Build APK
      run: ./gradlew assembleDebug
    - uses: actions/upload-artifact@v3
      with:
        name: app-debug
        path: app/build/outputs/apk/debug/app-debug.apk
```

---

## 📄 License

MIT License - Feel free to use and modify

---

## 📞 Support

For issues or questions:
- Check OpenClaw documentation
- Review Android developer docs
- Contact development team

---

**Last Updated:** 2026-03-12  
**Version:** 1.0.0  
**Build:** 1
