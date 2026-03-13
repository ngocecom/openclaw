# 📤 HƯỚNG DẪN UPLOAD LÊN GOOGLE DRIVE

**Folder đích:** https://drive.google.com/drive/folders/YOUR_FOLDER_ID  
**Account:** fachuhomes@gmail.com  
**Structure:** Personal MBA (7 categories)

---

## 🎯 CÁCH 1: DRAG & DROP (Nhanh nhất - Khuyên dùng)

### Bước 1: Mở Google Drive
```
https://drive.google.com/drive/my-drive
→ Click folder "openclaw"
```

### Bước 2: Drag folders từ workspace
Mở File Explorer: `C:\Users\mayao2\.openclaw\workspace\`

Drag lần lượt các folders vào Google Drive:
```
01-VALUE-CREATION/
02-MARKETING/
03-SALES/
04-VALUE-DELIVERY/
05-FINANCE/
06-HUMAN-SYSTEMS/
07-REFERENCES/
memory/
```

### Bước 3: Drag files gốc
```
MEMORY.md
USER.md
SOUL.md
TOOLS.md
AGENTS.md
IDENTITY.md
WORKSPACE-GUIDE.md
```

**Thời gian:** ~5 phút (tùy tốc độ internet)

---

## 🎯 CÁCH 2: GOOGLE DRIVE FOR DESKTOP (Sync tự động)

### Cài đặt:
1. Download: https://www.google.com/drive/download/
2. Install "Google Drive for Desktop"
3. Sign in: fachuhomes@gmail.com

### Cấu hình sync:
1. Mở Google Drive preferences
2. Click Settings (gear icon)
3. "My Drive" → "Stream files" hoặc "Mirror files"
4. Add folder: `C:\Users\mayao2\.openclaw\workspace\`

**Lợi ích:** Tự động sync mọi thay đổi

---

## 🎯 CÁCH 3: RCLONE (CLI - Cho advanced users)

### Cài đặt:
```powershell
winget install Rclone.Rclone
```

### Cấu hình:
```powershell
rclone config
# Chọn "n" (new remote)
# Type: Google Drive
# Follow OAuth flow
```

### Upload:
```powershell
rclone sync C:\Users\mayao2\.openclaw\workspace gdrive:/openclaw --progress
```

---

## 📁 CẤU TRÚC ĐÍCH TRÊN DRIVE

```
openclaw/
├── 01-VALUE-CREATION/
│   ├── README.md
│   ├── market-research/vincustom-products/
│   ├── product-ideas/
│   └── validation/
│
├── 02-MARKETING/
│   ├── README.md
│   ├── messaging/
│   ├── channels/
│   └── content/
│
├── 03-SALES/
│   ├── README.md
│   ├── onboarding/
│   └── conversion/
│
├── 04-VALUE-DELIVERY/
│   ├── README.md
│   ├── systems/
│   ├── processes/HEARTBEAT.md
│   ├── quality/
│   └── support/
│
├── 05-FINANCE/
│   ├── README.md
│   ├── costs/openrouter-config.json
│   ├── revenue/
│   ├── investments/
│   └── metrics/
│
├── 06-HUMAN-SYSTEMS/
│   ├── README.md
│   ├── communication/
│   ├── decisions/
│   ├── learning/
│   └── culture/
│
├── 07-REFERENCES/
│   ├── README.md
│   ├── books/
│   ├── articles/
│   ├── tools/
│   └── templates/
│
├── memory/
│   ├── YYYY-MM-DD.md
│   └── work-projects.md
│
└── [Root Files]
    ├── MEMORY.md
    ├── USER.md
    ├── SOUL.md
    ├── TOOLS.md
    ├── AGENTS.md
    ├── IDENTITY.md
    └── WORKSPACE-GUIDE.md
```

---

## ✅ CHECKLIST SAU KHI UPLOAD

- [ ] 7 folders chính (01- qua 07-)
- [ ] memory/ folder
- [ ] 8 README files (1 cho mỗi category + WORKSPACE-GUIDE)
- [ ] Root files (MEMORY.md, USER.md, etc.)
- [ ] vincustom-products/ trong 01-VALUE-CREATION/market-research/
- [ ] Google Sheet "Vincustom Ads Analysis" đã tồn tại

---

## 🔗 QUICK LINKS

| Resource | Link |
|----------|------|
| Google Drive | https://drive.google.com |
| OpenClaw Folder | https://drive.google.com/drive/my-drive → openclaw |
| Vincustom Sheet | https://docs.google.com/spreadsheets/d/1gHKsePGCxkrGq1CGsEfqwFVL44hTrlmwRVTGJ8ivwIw/edit |

---

## 📊ƯỚC LƯỢNG

**Total Size:** ~500 KB - 1 MB (text files only)  
**Upload Time:** 2-5 phút (10 Mbps connection)  
**Files Count:** ~30 files + folders

---

*Created: March 13, 2026*
