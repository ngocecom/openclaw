# 🐍 CÀI ĐẶT PYTHON & GOOGLE API LIBRARIES

**Date:** March 13, 2026  
**Status:** ⏳ Cần cài Python

---

## 📥 BƯỚC 1: CÀI PYTHON

### Option A: Microsoft Store (Nhanh nhất)

1. Mở Microsoft Store
2. Search: "Python 3.12"
3. Click "Get" hoặc "Install"
4. Đợi cài xong (~5 phút)

### Option B: Python.org (Chính thức)

1. Download: https://www.python.org/downloads/
2. Chọn: **Python 3.12.x** (mới nhất)
3. **QUAN TRỌNG:** ✅ Check "Add Python to PATH" khi cài
4. Click "Install Now"

### Verification

Sau khi cài, mở PowerShell mới và chạy:
```powershell
python --version
```

Kết quả mong đợi: `Python 3.12.x`

---

## 📦 BƯỚC 2: CÀI GOOGLE API LIBRARIES

Mở PowerShell và chạy:

```powershell
python -m pip install --upgrade google-api-python-client google-auth-httplib2 google-auth-oauthlib gspread
```

**Thời gian:** ~2-5 phút (tùy internet)

**Kết quả mong đợi:**
```
Successfully installed google-api-python-client-2.x.x google-auth-2.x.x gspread-6.x.x ...
```

---

## 🧪 BƯỚC 3: TEST API ACCESS

### Script Test Google Drive

File: `test-drive.py`

```python
#!/usr/bin/env python3
"""Test Google Drive API access"""

from google.oauth2.credentials import Credentials
from google_auth_oauthlib.flow import InstalledAppFlow
from googleapiclient.discovery import build
import json
import os

# Paths
CREDENTIALS_FILE = os.path.join(os.path.dirname(__file__), 'credentials.json')
TOKEN_FILE = os.path.join(os.path.dirname(__file__), 'token.json')

# Scopes
SCOPES = [
    'https://www.googleapis.com/auth/drive',
    'https://www.googleapis.com/auth/spreadsheets',
    'https://www.googleapis.com/auth/gmail.send'
]

def main():
    print("🔐 Google API Test Script")
    print("=" * 50)
    
    # Check credentials file
    if not os.path.exists(CREDENTIALS_FILE):
        print(f"❌ Credentials file not found: {CREDENTIALS_FILE}")
        return
    
    print(f"✅ Found credentials: {CREDENTIALS_FILE}")
    
    # Load credentials
    with open(CREDENTIALS_FILE) as f:
        creds_data = json.load(f)
    
    print(f"📋 Project ID: {creds_data['installed']['project_id']}")
    print(f"🆔 Client ID: {creds_data['installed']['client_id']}")
    
    # OAuth flow
    print("\n🌐 Opening browser for OAuth authorization...")
    flow = InstalledAppFlow.from_client_config(
        creds_data['installed'],
        scopes=SCOPES
    )
    creds = flow.run_local_server(port=0)
    
    print("✅ OAuth successful!")
    
    # Save token for future use
    with open(TOKEN_FILE, 'w') as f:
        f.write(creds.to_json())
    print(f"💾 Token saved: {TOKEN_FILE}")
    
    # Test Drive API
    print("\n📂 Testing Google Drive API...")
    service = build('drive', 'v3', credentials=creds)
    results = service.files().list(pageSize=5, fields="files(id, name)").execute()
    files = results.get('files', [])
    
    print(f"✅ Drive API connected!")
    print(f"📁 Recent files in Drive:")
    for file in files:
        print(f"   - {file['name']} ({file['id']})")
    
    # Test Sheets API
    print("\n📊 Testing Google Sheets API...")
    service = build('sheets', 'v4', credentials=creds)
    spreadsheet_list = service.spreadsheets().list().execute()
    spreadsheets = spreadsheet_list.get('spreadsheets', [])
    
    print(f"✅ Sheets API connected!")
    print(f"📋 Recent spreadsheets:")
    for sheet in spreadsheets[:5]:
        print(f"   - {sheet['name']} ({sheet['spreadsheetId']})")
    
    print("\n" + "=" * 50)
    print("🎉 ALL TESTS PASSED!")
    print("=" * 50)

if __name__ == '__main__':
    main()
```

### Chạy Test

```powershell
cd C:\Users\mayao2\.openclaw\workspace\07-REFERENCES\tools\google-credentials
python test-drive.py
```

**Kết quả mong đợi:**
1. Browser mở ra
2. Login với fachuhomes@gmail.com
3. Authorize "OpenClaw Automation"
4. List files trong Drive
5. List Sheets đã tạo

---

## 📊 BƯỚC 4: UPLOAD VINCUSTOM DATA

### Script Upload CSV to Google Sheets

File: `upload-vincustom.py`

```python
#!/usr/bin/env python3
"""Upload Vincustom products to Google Sheets"""

import gspread
from google.oauth2.credentials import Credentials
from google_auth_oauthlib.flow import InstalledAppFlow
import json
import csv
import os

SCOPES = [
    'https://www.googleapis.com/auth/spreadsheets',
    'https://www.googleapis.com/auth/drive'
]

def main():
    print("📤 Vincustom Data Upload Script")
    print("=" * 50)
    
    # Load credentials
    creds_data = json.load(open('credentials.json'))
    token_file = 'token.json'
    
    # Load or create credentials
    if os.path.exists(token_file):
        print(f"💾 Loading existing token: {token_file}")
        creds = Credentials.from_authorized_user_file(token_file, SCOPES)
    else:
        print("🔐 Starting OAuth flow...")
        flow = InstalledAppFlow.from_client_config(
            creds_data['installed'],
            scopes=SCOPES
        )
        creds = flow.run_local_server(port=0)
        
        # Save token
        with open(token_file, 'w') as f:
            f.write(creds.to_json())
        print(f"💾 Token saved: {token_file}")
    
    # Authorize
    client = gspread.authorize(creds)
    print("✅ Google Sheets authorized!")
    
    # Read CSV
    csv_file = r'C:\Users\mayao2\.openclaw\workspace\01-VALUE-CREATION\market-research\vincustom-products\vincustom-products-full.csv'
    print(f"\n📖 Reading CSV: {csv_file}")
    
    with open(csv_file, 'r', encoding='utf-8') as f:
        data = list(csv.reader(f))
    
    print(f"✅ Loaded {len(data)} rows")
    
    # Create spreadsheet
    print("\n📊 Creating Google Sheet...")
    spreadsheet = client.create('Vincustom Products 2026')
    print(f"✅ Created: {spreadsheet.title}")
    
    # Upload data
    print("📤 Uploading data...")
    worksheet = spreadsheet.sheet1
    worksheet.update('A1', data)
    print(f"✅ Uploaded {len(data)} rows to sheet!")
    
    # Share with duongvanngoc.vn@gmail.com
    print("\n📧 Sharing with duongvanngoc.vn@gmail.com...")
    spreadsheet.share('duongvanngoc.vn@gmail.com', perm_type='user', role='writer')
    print("✅ Shared!")
    
    # Print URL
    print("\n" + "=" * 50)
    print(f"🎉 SUCCESS!")
    print(f"📊 Google Sheet URL:")
    print(f"   {spreadsheet.url}")
    print("=" * 50)

if __name__ == '__main__':
    main()
```

### Chạy Upload

```powershell
cd C:\Users\mayao2\.openclaw\workspace\07-REFERENCES\tools\google-credentials
python upload-vincustom.py
```

**Kết quả:**
- ✅ Google Sheet được tạo
- ✅ Data 11 products được upload
- ✅ Share với duongvanngoc.vn@gmail.com
- ✅ URL hiển thị để truy cập ngay

---

## ✅ CHECKLIST

- [ ] Cài Python 3.12
- [ ] Cài Google API libraries
- [ ] Chạy test-drive.py
- [ ] Chạy upload-vincustom.py
- [ ] Truy cập Google Sheet từ URL

---

## 📞 CẦN GIÚP?

Nếu gặp lỗi:
1. Check Python version: `python --version`
2. Check pip packages: `pip list | findstr google`
3. Delete token.json và chạy lại để re-authorize
4. Báo cho OpenClaw assistant

---

*Tạo: March 13, 2026 10:25 AM*
