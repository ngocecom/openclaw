# 🚀 Quick Start Guide - OpenClaw Android App

**Thưa sếp, đây là hướng dẫn nhanh để build APK!**

---

## 📋 Bước 1: Cài Đặt Android Studio

1. **Download Android Studio:**
   - https://developer.android.com/studio
   - Chọn phiên bản mới nhất cho Windows

2. **Cài đặt:**
   - Chạy installer
   - Chọn default options
   - Đợi cài đặt hoàn tất (có thể mất 10-15 phút)

3. **Mở Android Studio:**
   - Lần đầu mở sẽ download thêm SDK components

---

## 📋 Bước 2: Mở Project

1. **Mở Android Studio**

2. **File → Open**

3. **Chọn folder:**
   ```
   C:\Users\mayao2\.openclaw\workspace\openclaw-android-app
   ```

4. **Click "OK"**

5. **Đợi Gradle sync** (2-5 phút lần đầu)

---

## 📋 Bước 3: Cấu Hình

### 3.1. Update Config.kt

Mở file: `app/src/main/java/com/openclaw/chat/Config.kt`

**Sửa các giá trị:**

```kotlin
// Tìm IP của PC (Windows):
// 1. Mở Command Prompt
// 2. Gõ: ipconfig
// 3. Tìm "IPv4 Address"

const val OPENCLAW_HOST = "192.168.1.XXX" // Thay bằng IP PC của sếp

const val OPENCLAW_PORT = 18789 // Giữ nguyên

const val API_TOKEN = "your-token" // Lấy từ OpenClaw config
```

### 3.2. Lấy API Token từ OpenClaw

1. Mở Control UI: http://127.0.0.1:18789
2. Vào tab "Config"
3. Tìm API token hoặc tạo mới
4. Copy và paste vào Config.kt

---

## 📋 Bước 4: Build APK

### Option A: Build từ Android Studio (Recommended)

1. **Build → Build Bundle(s) / APK(s) → Build APK(s)**

2. **Đợi build hoàn tất** (2-5 phút)

3. **Click "locate"** khi build xong

4. **APK sẽ ở:**
   ```
   app\build\outputs\apk\debug\app-debug.apk
   ```

### Option B: Build từ Command Line

```bash
cd C:\Users\mayao2\.openclaw\workspace\openclaw-android-app

# Build debug APK
gradlew.bat assembleDebug

# Build release APK (cần signing)
gradlew.bat assembleRelease
```

---

## 📋 Bước 5: Cài Đặt Lên Điện Thoại

### Option A: Qua USB (Recommended)

1. **Bật Developer Options trên điện thoại:**
   - Settings → About Phone
   - Tap "Build Number" 7 lần
   - Quay lại Settings → System → Developer Options
   - Bật "USB Debugging"

2. **Connect điện thoại vào PC qua USB**

3. **Cài đặt qua ADB:**
   ```bash
   adb install app/build/outputs/apk/debug/app-debug.apk
   ```

### Option B: Transfer APK thủ công

1. **Copy APK file** từ PC sang điện thoại

2. **Trên điện thoại:**
   - Mở File Manager
   - Tìm file APK
   - Tap để cài đặt
   - Allow "Install from Unknown Sources" nếu được hỏi

3. **Mở app và test!**

---

## 📋 Bước 6: Test App

1. **Mở OpenClaw Chat app** trên điện thoại

2. **Kiểm tra connection status:**
   - Thanh trên cùng màu xanh = Connected ✓
   - Màu đỏ = Disconnected ✗

3. **Gửi tin nhắn test:**
   ```
   Hello
   ```

4. **Nếu thấy response** = Thành công! 🎉

---

## 🐛 Troubleshooting

### Lỗi: "Cannot connect to OpenClaw"

**Check:**
1. OpenClaw Gateway đang chạy?
   ```bash
   openclaw status
   ```

2. IP address đúng không?
   - Check lại Config.kt
   - Đảm bảo PC và điện thoại cùng WiFi

3. Firewall blocking?
   - Windows Firewall → Allow port 18789
   - Hoặc tạm disable firewall để test

### Lỗi: "Build failed"

**Fix:**
```bash
# Clean và rebuild
gradlew.bat clean
gradlew.bat build

# Hoặc trong Android Studio:
# File → Invalidate Caches / Restart
```

### Lỗi: "App crashes on launch"

**Check:**
1. Config.kt đã update chưa?
2. OpenClaw Gateway có running không?
3. Check Logcat trong Android Studio để xem error

---

## 📱 Screenshots

### Expected UI:

```
┌─────────────────────────┐
│ ✓ Connected to OpenClaw│ ← Status bar (xanh)
├─────────────────────────┤
│                         │
│    [OpenClaw]           │
│    Hello! How can       │
│    I help? 🦞           │
│                         │
│             [You]       │
│         Hello           │
│                         │
│    [OpenClaw]           │
│    I can help with...   │
│                         │
├─────────────────────────┤
│ [Type message...]  [➤] │ ← Input field
└─────────────────────────┘
```

---

## 🎯 Next Steps

### Sau khi app chạy thành công:

1. **Customize UI:**
   - Đổi màu trong `Color.kt`
   - Đổi icon trong `res/mipmap/`
   - Đổi tên app trong `strings.xml`

2. **Add features:**
   - Voice input
   - File attachments
   - Push notifications
   - Offline mode

3. **Build Release APK:**
   - Generate signing key
   - Build release APK
   - Share với team/users

---

## 📞 Need Help?

**Nếu gặp vấn đề:**

1. Check README.md trong project folder
2. Check Android Studio logs
3. Search error message trên Google
4. Hỏi OpenClaw documentation

---

**🦞 Good luck thưa sếp!**

Em đã tạo project hoàn chỉnh, sếp chỉ cần:
1. ✅ Cài Android Studio
2. ✅ Update Config.kt
3. ✅ Build APK
4. ✅ Install lên phone

**Total time:** ~30-60 phút (lần đầu)
