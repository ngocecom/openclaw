# 🚀 QUICK START - GOOGLE API SETUP

**Tóm tắt 5 bước chính (10 phút)**

---

## 1️⃣ TẠO PROJECT (2 phút)

```
https://console.cloud.google.com
→ NEW PROJECT → Tên: openclaw-automation → CREATE
```

---

## 2️⃣ ENABLE APIs (3 phút)

Search và enable 3 APIs:
```
✅ Google Drive API
✅ Google Sheets API  
✅ Gmail API
```

---

## 3️⃣ OAUTH CONSENT (3 phút)

```
APIs & Services → OAuth consent screen
→ External → CREATE
→ App name: OpenClaw Automation
→ Add test users: fachuhomes@gmail.com, duongvanngoc.vn@gmail.com
```

---

## 4️⃣ CREATE CREDENTIALS (2 phút)

```
Credentials → CREATE CREDENTIALS → OAuth client ID
→ Application type: Desktop app
→ Name: OpenClaw Desktop Client
→ CREATE → DOWNLOAD JSON
```

---

## 5️⃣ LƯU FILE (30 giây)

```
credentials.json → Move to:
C:\Users\mayao2\.openclaw\workspace\07-REFERENCES\tools\google-credentials\
```

---

## ✅ XONG!

Giờ có thể dùng Python scripts để:
- Upload files tự động
- Tạo Google Sheets
- Gửi emails

**Chi tiết đầy đủ:** `SETUP-GOOGLE-API.md`

---

*Tạo: March 13, 2026*
