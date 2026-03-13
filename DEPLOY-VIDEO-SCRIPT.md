# 🎥 VIDEO HƯỚNG DẪN DEPLOY LÊN GITHUB PAGES

**Created:** March 13, 2026  
**Duration:** 5 phút  
**Difficulty:** Easy ⭐ (1/5)

---

## 📋 MỤC LỤC

1. Chuẩn bị (30 giây)
2. Mở PowerShell (30 giây)
3. Cấu hình Git (30 giây)
4. Commit code (1 phút)
5. Push lên GitHub (2 phút)
6. Enable GitHub Pages (1 phút)
7. Kiểm tra kết quả (30 giây)

---

## 🎬 CẢNH 1: CHUẨN BỊ (30 GIÂY)

### Màn hình hiển thị:
```
✅ Checklist:
□ GitHub account: https://github.com/ngocecom
□ Repository: https://github.com/ngocecom/openclaw
□ Token: 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
□ Workspace: C:\Users\mayao2\.openclaw\workspace
```

### Voiceover:
"Chào bạn! Hôm nay mình sẽ hướng dẫn deploy Mission Control Dashboard lên GitHub Pages chỉ với 5 phút!"

---

## 🎬 CẢNH 2: MỞ POWERSHELL (30 GIÂY)

### Bước 2.1: Mở Start Menu
- Click **Windows Start** (góc trái dưới)
- Gõ: **PowerShell**

### Bước 2.2: Mở PowerShell
- Click vào **Windows PowerShell**
- **KHÔNG CẦN** Run as Administrator

### Bước 2.3: Navigate to Workspace
Gõ lệnh:
```powershell
cd C:\Users\mayao2\.openclaw\workspace
```

**Kiểm tra:**
```powershell
dir
```
Phải thấy các folders: `01-VALUE-CREATION`, `memory`, etc.

---

## 🎬 CẢNH 3: CẤU HÌNH GIT (30 GIÂY)

### Bước 3.1: Set Username
```powershell
git config --global user.name "ngocecom"
```

### Bước 3.2: Set Email
```powershell
git config --global user.email "fachuhomes@gmail.com"
```

### Bước 3.3: Kiểm tra
```powershell
git config --list
```

**Kết quả mong đợi:**
```
user.name=ngocecom
user.email=fachuhomes@gmail.com
```

---

## 🎬 CẢNH 4: COMMIT CODE (1 PHÚT)

### Bước 4.1: Add Remote (nếu chưa có)
```powershell
git remote add origin https://github.com/ngocecom/openclaw.git
```

**Nếu lỗi "remote already exists":**
```powershell
git remote remove origin
git remote add origin https://github.com/ngocecom/openclaw.git
```

### Bước 4.2: Kiểm tra Remote
```powershell
git remote -v
```

**Kết quả:**
```
origin  https://github.com/ngocecom/openclaw.git (fetch)
origin  https://github.com/ngocecom/openclaw.git (push)
```

### Bước 4.3: Stage Tất Cả Files
```powershell
git add -A
```

### Bước 4.4: Kiểm tra Files
```powershell
git status
```

**Kết quả:**
```
Changes to be committed:
  new file:   01-VALUE-CREATION/multi-agent-system/mission-control/index.html
  new file:   ...
```

### Bước 4.5: Commit
```powershell
git commit -m "Deploy Mission Control Dashboard v2.0"
```

**Kết quả:**
```
[main (root-commit) abc1234] Deploy Mission Control Dashboard v2.0
 15 files changed, 1234 insertions(+)
 create mode 100644 01-VALUE-CREATION/multi-agent-system/mission-control/index.html
 ...
```

---

## 🎬 CẢNH 5: PUSH LÊN GITHUB (2 PHÚT) ⚠️ QUAN TRỌNG

### Bước 5.1: Đổi Branch thành Main
```powershell
git branch -M main
```

### Bước 5.2: Push
```powershell
git push -u origin main
```

### Bước 5.3: Nhập Username
**Màn hình sẽ hiện:**
```
Username for 'https://github.com':
```

**Gõ:** `ngocecom` → Enter

### Bước 5.4: Nhập Password/Token ⚠️
**Màn hình sẽ hiện:**
```
Password for 'https://ngocecom@github.com':
```

**Lưu ý quan trọng:**
- ❌ **KHÔNG nhập password GitHub thông thường**
- ✅ **Nhập Personal Access Token**
- Token sẽ **KHÔNG HIỂN THỊ** khi gõ (bảo mật)

**Gõ:** `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` → Enter

### Bước 5.5: Đợi Push Hoàn Tất
**Kết quả mong đợi:**
```
Enumerating objects: 15, done.
Counting objects: 100% (15/15), done.
Delta compression using up to 8 threads
Compressing objects: 100% (12/12), done.
Writing objects: 100% (15/15), 28.50 KiB | 2.85 MiB/s, done.
Total 15 (delta 2), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (2/2)
To https://github.com/ngocecom/openclaw.git
 * [new branch]      main -> main
Branch 'main' set up to track remote branch 'main' from 'origin'.
```

---

## 🎬 CẢNH 6: ENABLE GITHUB PAGES (1 PHÚT)

### Bước 6.1: Mở GitHub Settings
Mở browser, vào:
```
https://github.com/ngocecom/openclaw/settings/pages
```

### Bước 6.2: Tìm "Build and deployment"
Scroll xuống section **Build and deployment**

### Bước 6.3: Cấu hình Source
- **Source:** Chọn "Deploy from branch"

### Bước 6.4: Chọn Branch
- **Branch:** Dropdown sẽ hiện "None" → Chọn **"main"**
- **Folder:** Chọn **"/ (root)"** (hoặc folder cụ thể nếu muốn)

### Bước 6.5: Save
Click nút **Save**

### Bước 6.6: Đợi Deploy
**Màn hình sẽ hiện:**
```
Your site is live at https://ngocecom.github.io/openclaw/
```

**Thời gian đợi:** 2-3 phút

---

## 🎬 CẢNH 7: KIỂM TRA KẾT QUẢ (30 GIÂY)

### Bước 7.1: Mở Dashboard URL
```
https://ngocecom.github.io/openclaw/01-VALUE-CREATION/multi-agent-system/mission-control/
```

### Bước 7.2: Kiểm Tra
**Phải thấy:**
- ✅ Header: "🎮 Mission Control"
- ✅ Agent cards (Henry, Researcher, Analyst, etc.)
- ✅ Status badges (Online, Busy, Idle)
- ✅ Progress bars với animations
- ✅ Metrics panel

### Bước 7.3: Test Responsive
- Resize browser window
- Check mobile view (F12 → Device toolbar)

---

## 🔧 XỬ LÝ SỰ CỐ

### Lỗi 1: "Authentication failed"
**Nguyên nhân:** Token sai hoặc hết hạn

**Fix:**
1. Mở: https://github.com/settings/tokens
2. Tạo token mới
3. Push lại với token mới

### Lỗi 2: "remote origin already exists"
**Fix:**
```powershell
git remote remove origin
git remote add origin https://github.com/ngocecom/openclaw.git
```

### Lỗi 3: "branch 'main' not found"
**Fix:**
```powershell
git branch -M main
git push -u origin main
```

### Lỗi 4: GitHub Pages không hiển thị
**Fix:**
1. Check Settings → Pages
2. Đảm bảo Branch = main
3. Đợi thêm 5 phút
4. Clear browser cache (Ctrl+Shift+Delete)

---

## ✅ CHECKLIST CUỐI CÙNG

- [ ] PowerShell mở tại workspace
- [ ] Git config username/email
- [ ] Remote origin added
- [ ] Files committed
- [ ] Push thành công lên GitHub
- [ ] GitHub Pages enabled
- [ ] Dashboard live tại URL
- [ ] Dashboard hiển thị đúng

---

## 🎯 URL SAU KHI DEPLOY

**Dashboard:**
```
https://ngocecom.github.io/openclaw/01-VALUE-CREATION/multi-agent-system/mission-control/
```

**Repository:**
```
https://github.com/ngocecom/openclaw
```

**Settings:**
```
https://github.com/ngocecom/openclaw/settings/pages
```

---

## 📞 CẦN HỖ TRỢ?

Nếu gặp khó khăn:
1. Check lại từng bước trong script này
2. Screenshot lỗi → Gửi cho OpenClaw assistant
3. Check GitHub status: https://www.githubstatus.com

---

**Chúc bạn deploy thành công!** 🚀

*Created: March 13, 2026*  
*Version: 1.0.0*
