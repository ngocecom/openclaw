# 📸 HƯỚNG DẪN TẠO GITHUB TOKEN (2 PHÚT)

**Created:** March 13, 2026  
**Time Required:** 2 minutes  
**Difficulty:** Easy

---

## 🎯 MỤC ĐÍCH

Tạo Personal Access Token (PAT) để deploy dashboard lên GitHub Pages.

---

## 📋 CÁC BƯỚC THỰC HIỆN

### **BƯỚC 1: MỞ GITHUB TOKEN SETTINGS** (10 giây)

**Click vào link này:**
```
https://github.com/settings/tokens
```

Hoặc:
1. Mở GitHub.com
2. Click avatar (góc phải trên)
3. Click **Settings**
4. Scroll xuống → Click **Developer settings**
5. Click **Personal access tokens** → **Tokens (classic)**

---

### **BƯỚC 2: TẠO TOKEN MỚI** (30 giây)

1. **Click nút xanh:** "Generate new token (classic)"
   - ⚠️ KHÔNG chọn "Generate new token (fine-grained)" - không hoạt động!

2. **Điền thông tin:**

   **Note:** 
   ```
   OpenClaw Dashboard
   ```

   **Expiration:** 
   ```
   No expiration
   ```
   (hoặc chọn 90 days nếu muốn bảo mật hơn)

---

### **BƯỚC 3: CHỌN SCOPES** (20 giây)

**QUAN TRỌNG:** Check vào ô **`repo`**

```
✅ repo  Full control of private repositories
```

Khi check `repo`, tất cả sub-options bên dưới sẽ tự động check:
- ✅ repo:status
- ✅ repo_deployment
- ✅ public_repo
- ✅ repo:invite
- ✅ security_events

**Không cần check scopes khác!**

---

### **BƯỚC 4: GENERATE TOKEN** (10 giây)

1. **Scroll xuống cuối trang**
2. **Click nút xanh:** "Generate token"
3. **Đợi 1-2 giây**

---

### **BƯỚC 5: COPY TOKEN** (30 giây) ⚠️ QUAN TRỌNG NHẤT!

**MÀN HÌNH SẼ HIỂN THỊ:**

```
✅ Personal access tokens (classic)

Your new token: ghp_xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

⚠️ Make sure to copy your personal access token now. 
   You won't be able to see it again!
```

**HÀNH ĐỘNG:**

1. **Click icon copy** (📋) bên cạnh token
2. **HOẶC** click-drag để select toàn bộ token
3. **Ctrl+C** để copy

**LUU TOKEN VÀO ĐÂU?**

- ✅ **Notepad:** Tạm thời (xóa sau khi paste vào script)
- ✅ **Password Manager:** Bitwarden, 1Password (recommended)
- ✅ **File an toàn:** `GITHUB-TOKEN-TEMPLATE.txt` (em đã tạo)
- ❌ **KHÔNG** commit vào git
- ❌ **KHÔNG** gửi email/chat

---

### **BƯỚC 6: KIỂM TRA TOKEN** (10 giây)

Token hợp lệ có format:
```
ghp_xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
```

- **Bắt đầu bằng:** `ghp_`
- **Độ dài:** 40 ký tự
- **Ký tự:** Chữ thường + số

**Ví dụ token thật:**
```
ghp_1a2b3c4d5e6f7g8h9i0j1k2l3m4n5o6p7q8r
```

---

## ✅ SAU KHI CÓ TOKEN

### **Option A: Lưu vào File Template**

1. Mở file: `GITHUB-TOKEN-TEMPLATE.txt`
2. Paste token vào section: `[PASTE YOUR TOKEN HERE]`
3. Save file
4. **KHÔNG commit file này vào git!**

### **Option B: Dùng Ngay**

1. Mở PowerShell
2. Chạy: `.\deploy-to-github.ps1`
3. Paste token khi được hỏi
4. Token tự động lưu trong git credential store

---

## 🔧 TROUBLESHOOTING

### Không thấy nút "Generate new token (classic)"?
- Check bạn đã login đúng account chưa
- Account cần được verify email
- Try: https://github.com/settings/personal-access-tokens

### Token không bắt đầu bằng `ghp_`?
- Bạn đã tạo fine-grained token (không hoạt động)
- Delete và tạo lại với "classic" option

### Quên copy token?
- Token không xem lại được!
- Delete token cũ
- Tạo token mới
- Update deployment script

---

## 📸 SCREENSHOT MÔ TẢ

**Màn hình 1: Token Settings**
```
┌─────────────────────────────────────────┐
│ Personal access tokens                  │
├─────────────────────────────────────────┤
│ [Generate new token ▼]                  │
│   → classic                             │
│   → fine-grained                        │
└─────────────────────────────────────────┘
```

**Màn hình 2: Token Form**
```
┌─────────────────────────────────────────┐
│ Note: [OpenClaw Dashboard________]      │
│ Expiration: [No expiration ▼]           │
│                                         │
│ Scopes:                                 │
│ ✅ repo  Full control of private...     │
│    [ ] admin:org                        │
│    [ ] write:org                        │
│    [ ] read:org                         │
│    ...                                  │
│                                         │
│ [Cancel]  [Generate token]              │
└─────────────────────────────────────────┘
```

**Màn hình 3: Token Generated**
```
┌─────────────────────────────────────────┐
│ ✅ Personal access tokens (classic)     │
├─────────────────────────────────────────┤
│ Your new token:                         │
│ ghp_1a2b3c4d5e6f7g8h9i0j1k2l3m4n5o6p7q8r│
│ [📋 Copy]                               │
│                                         │
│ ⚠️ Make sure to copy now!               │
│    You won't be able to see it again!   │
└─────────────────────────────────────────┘
```

---

## 🎯 CHECKLIST

- [ ] Đã login GitHub
- [ ] Đã mở Token Settings
- [ ] Đã click "Generate new token (classic)"
- [ ] Đã điền Note: "OpenClaw Dashboard"
- [ ] Đã chọn Expiration: "No expiration"
- [ ] Đã check scope: ✅ repo
- [ ] Đã click "Generate token"
- [ ] Đã COPY token (bắt đầu bằng ghp_)
- [ ] Đã lưu token vào nơi an toàn
- [ ] Sẵn sàng chạy deployment script

---

## 📞 CẦN HỖ TRỢ?

Nếu gặp khó khăn:
1. Check screenshots trên
2. Đọc troubleshooting section
3. Báo cho OpenClaw assistant

---

**Thời gian ước tính:** 2 phút  
**Độ khó:** ⭐ (1/5)

*Created: March 13, 2026*
