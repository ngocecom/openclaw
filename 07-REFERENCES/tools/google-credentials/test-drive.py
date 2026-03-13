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
