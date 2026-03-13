# 🔑 HƯỚNG DẪN LẤY GOOGLE API CREDENTIALS

**Date:** March 13, 2026  
**Account:** fachuhomes@gmail.com  
**APIs Needed:** Google Drive, Google Sheets, Gmail  
**Time:** 10-15 phút

---

## 📋 TỔNG QUAN

### Mục đích:
Lấy API credentials để OpenClaw truy cập Google Drive/Sheets/Gmail programmatic (không cần browser automation)

### Kết quả:
- ✅ `credentials.json` - OAuth 2.0 credentials
- ✅ API keys cho Drive, Sheets, Gmail
- ✅ Python/Node.js scripts để upload files tự động

---

## 🚀 CÁC BƯỚC THỰC HIỆN

### BƯỚC 1: Truy cập Google Cloud Console (1 phút)

**URL:** https://console.cloud.google.com

1. Mở link trên trong Chrome/Edge
2. Login: **fachuhomes@gmail.com**
3. Nếu hỏi "Welcome to Google Cloud" → Click **"I AGREE"**

---

### BƯỚC 2: Tạo Project Mới (2 phút)

1. Click dropdown project (trên cùng, bên trái)
2. Click **"NEW PROJECT"** hoặc **"+ CREATE PROJECT"**
3. Nhập tên project: **`openclaw-automation`**
4. Click **"CREATE"**
5. Đợi project tạo xong (~30 giây)
6. Click **"SELECT PROJECT"**

---

### BƯỚC 3: Enable APIs (3 phút)

#### 3a. Google Drive API
1. Menu trái: **APIs & Services** → **Library**
2. Search: **"Google Drive API"**
3. Click **"Google Drive API"**
4. Click **"ENABLE"**
5. Đợi enable xong

#### 3b. Google Sheets API
1. Search: **"Google Sheets API"**
2. Click **"Google Sheets API"**
3. Click **"ENABLE"**
4. Đợi enable xong

#### 3c. Gmail API
1. Search: **"Gmail API"**
2. Click **"Gmail API"**
3. Click **"ENABLE"**
4. Đợi enable xong

---

### BƯỚC 4: Tạo OAuth Credentials (5 phút)

#### 4a. Configure Consent Screen
1. Menu trái: **APIs & Services** → **OAuth consent screen**
2. Chọn **"External"** → Click **"CREATE"**
3. Điền thông tin:
   - **App name:** `OpenClaw Automation`
   - **User support email:** `fachuhomes@gmail.com`
   - **Developer contact:** `fachuhomes@gmail.com`
4. Click **"SAVE AND CONTINUE"**
5. **Scopes:** Click **"ADD OR REMOVE SCOPES"**
   - Check: `.../auth/drive` (Google Drive)
   - Check: `.../auth/spreadsheets` (Google Sheets)
   - Check: `.../auth/gmail.send` (Gmail)
   - Click **"UPDATE"** → **"SAVE AND CONTINUE"**
6. **Test users:** Click **"ADD USERS"**
   - Add: `fachuhomes@gmail.com`
   - Add: `duongvanngoc.vn@gmail.com`
   - Click **"ADD"** → **"SAVE AND CONTINUE"**
7. Click **"BACK TO DASHBOARD"**

#### 4b. Create OAuth Client ID
1. Menu trái: **APIs & Services** → **Credentials**
2. Click **"+ CREATE CREDENTIALS"** → **"OAuth client ID"**
3. **Application type:** Chọn **"Desktop app"**
4. **Name:** `OpenClaw Desktop Client`
5. Click **"CREATE"**
6. **POPUP HIỂN THỊ CREDENTIALS** → Click **"DOWNLOAD JSON"**
7. Lưu file: **`credentials.json`**

---

### BƯỚC 5: Lưu Credentials (1 phút)

**File downloaded:** `credentials.json`

**Di chuyển vào workspace:**
```
C:\Users\mayao2\.openclaw\workspace\07-REFERENCES\tools\google-credentials\credentials.json
```

**Hoặc tạo folder:**
```powershell
New-Item -ItemType Directory -Force -Path "C:\Users\mayao2\.openclaw\workspace\07-REFERENCES\tools\google-credentials"
Move-Item credentials.json "C:\Users\mayao2\.openclaw\workspace\07-REFERENCES\tools\google-credentials\"
```

---

## 🔐 CREDENTIALS STRUCTURE

File `credentials.json` sẽ có format:

```json
{
  "installed": {
    "client_id": "123456789-abc123def456.apps.googleusercontent.com",
    "project_id": "openclaw-automation",
    "auth_uri": "https://accounts.google.com/o/oauth2/auth",
    "token_uri": "https://oauth2.googleapis.com/token",
    "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
    "client_secret": "GOCSPX-abc123def456",
    "redirect_uris": [
      "http://localhost:8080"
    ]
  }
}
```

**⚠️ QUAN TRỌNG:**
- `client_id` - Public identifier
- `client_secret` - **GIỮ BÍ MẬT!** Không share
- `project_id` - `openclaw-automation`

---

## 🐍 TEST API VỚI PYTHON

### Cài đặt libraries:
```powershell
pip install --upgrade google-api-python-client google-auth-httplib2 google-auth-oauthlib
```

### Script test Google Drive:
```python
# test-drive.py
from google.oauth2.credentials import Credentials
from google_auth_oauthlib.flow import InstalledAppFlow
from googleapiclient.discovery import build
import json

# Load credentials
with open('credentials.json') as f:
    creds_data = json.load(f)

# OAuth flow
SCOPES = [
    'https://www.googleapis.com/auth/drive',
    'https://www.googleapis.com/auth/spreadsheets',
    'https://www.googleapis.com/auth/gmail.send'
]

flow = InstalledAppFlow.from_client_config(
    creds_data['installed'],
    scopes=SCOPES
)
creds = flow.run_local_server(port=0)

# Test Drive API
service = build('drive', 'v3', credentials=creds)
results = service.files().list(pageSize=5, fields="files(id, name)").execute()
files = results.get('files', [])

print("✅ Google Drive API connected!")
print("Recent files:")
for file in files:
    print(f"  - {file['name']} ({file['id']})")
```

### Chạy test:
```powershell
python test-drive.py
```

Browser sẽ mở → Login → Authorize → Copy code → Paste vào terminal

**Kết quả:** List 5 files gần nhất trong Drive ✅

---

## 📊 UPLOAD FILE TỰ ĐỘNG

### Script upload CSV lên Google Sheet:
```python
# upload-to-sheets.py
import gspread
from google.oauth2.credentials import Credentials
from google_auth_oauthlib.flow import InstalledAppFlow
import csv

SCOPES = [
    'https://www.googleapis.com/auth/spreadsheets',
    'https://www.googleapis.com/auth/drive'
]

# OAuth
flow = InstalledAppFlow.from_client_config(
    json.load(open('credentials.json'))['installed'],
    scopes=SCOPES
)
creds = flow.run_local_server(port=0)

# Create spreadsheet
client = gspread.authorize(creds)
sheet = client.create('Vincustom Products 2026')

# Import CSV
with open('vincustom-products-full.csv', 'r') as f:
    data = list(csv.reader(f))
    sheet.update('A1', data)

print(f"✅ Created: {sheet.url}")
```

### Chạy:
```powershell
python upload-to-sheets.py
```

**Kết quả:** Google Sheet được tạo với data đầy đủ! 🎉

---

## 🔗 URLs QUAN TRỌNG

| Resource | URL |
|----------|-----|
| Google Cloud Console | https://console.cloud.google.com |
| API Library | https://console.cloud.google.com/apis/library |
| Credentials | https://console.cloud.google.com/apis/credentials |
| OAuth Consent | https://console.cloud.google.com/apis/credentials/consent |

---

## ✅ CHECKLIST

- [ ] Tạo Google Cloud project: `openclaw-automation`
- [ ] Enable Google Drive API
- [ ] Enable Google Sheets API
- [ ] Enable Gmail API
- [ ] Configure OAuth consent screen
- [ ] Create OAuth client ID (Desktop app)
- [ ] Download `credentials.json`
- [ ] Lưu vào workspace
- [ ] Test với Python script
- [ ] Upload CSV lên Google Sheet

---

## 🛠️ ỨNG DỤNG CHO OPENCLAW

### Sau khi có credentials, OpenClaw có thể:

1. **Upload files tự động**
   - Workspace → Google Drive
   - Không cần browser automation

2. **Tạo Google Sheets**
   - Từ CSV data
   - Auto-populate với reports

3. **Gửi email reports**
   - Daily reports via Gmail
   - Attach files

4. **Sync 2 chiều**
   - Drive → Workspace (download)
   - Workspace → Drive (upload)

---

## 📞 CẦN GIÚP?

Nếu gặp khó khăn ở bước nào:
1. Check screenshot trong folder `07-REFERENCES/tools/google-credentials/`
2. Báo cho OpenClaw assistant
3. Google Cloud docs: https://cloud.google.com/docs

---

## ⚠️ SECURITY NOTES

**Giữ bí mật:**
- ❌ Không share `client_secret`
- ❌ Không commit `credentials.json` vào git public
- ❌ Không post lên GitHub/GitLab public

**Best practices:**
- ✅ Add `.gitignore` cho `credentials.json`
- ✅ Dùng environment variables
- ✅ Rotate credentials định kỳ

---

*Tạo: March 13, 2026 9:55 AM VN Time*  
*Estimated time: 10-15 phút*
