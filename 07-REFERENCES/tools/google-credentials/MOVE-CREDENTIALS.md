# 📥 DI CHUYỂN CREDENTIALS FILE VÀO WORKSPACE

**Date:** March 13, 2026  
**Status:** ✅ OAuth Client Created

---

## 📁 FILE ĐÃ DOWNLOAD

**File:** `client_secret_465385173022-qi5dopqpe95l1lev3l4k5pe62vq0nio8.apps.googleusercontent.com.json`

**Location:** `C:\Users\mayao2\Downloads\`

---

## 🎯 DI CHUYỂN FILE

### Option 1: PowerShell (Khuyên dùng)

```powershell
# Tạo folder nếu chưa có
New-Item -ItemType Directory -Force -Path "C:\Users\mayao2\.openclaw\workspace\07-REFERENCES\tools\google-credentials"

# Di chuyển file
Move-Item "C:\Users\mayao2\Downloads\client_secret_*.json" `
          "C:\Users\mayao2\.openclaw\workspace\07-REFERENCES\tools\google-credentials\credentials.json"
```

### Option 2: File Explorer

1. Mở: `C:\Users\mayao2\Downloads\`
2. Tìm file: `client_secret_*.json`
3. Cut (Ctrl+X)
4. Mở: `C:\Users\mayao2\.openclaw\workspace\07-REFERENCES\tools\google-credentials\`
5. Paste (Ctrl+V)
6. Rename thành: `credentials.json`

---

## ✅ VERIFICATION

Sau khi di chuyển, check file tồn tại:

```powershell
Test-Path "C:\Users\mayao2\.openclaw\workspace\07-REFERENCES\tools\google-credentials\credentials.json"
```

Kết quả: `True` ✅

---

## 🔐 SECURITY

File đã được thêm vào `.gitignore`:
```
credentials.json
client_secret.json
token.json
*.pyc
__pycache__/
```

✅ An toàn để commit workspace (credentials không bị leak)

---

## 🐍 TEST API ACCESS

Sau khi có credentials, test với Python:

```powershell
cd C:\Users\mayao2\.openclaw\workspace\07-REFERENCES\tools\google-credentials
python test-drive.py
```

Script sẽ:
1. Mở browser để login
2. Authorize OAuth
3. List files trong Google Drive
4. Confirm API hoạt động ✅

---

*Tạo: March 13, 2026*
