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
