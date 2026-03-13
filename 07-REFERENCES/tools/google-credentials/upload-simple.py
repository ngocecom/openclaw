#!/usr/bin/env python3
"""Upload Vincustom products to Google Sheets - Simple Version"""

import gspread
from google.oauth2.credentials import Credentials
from google.auth.transport.requests import Request
import json
import csv
import os
import webbrowser
from http.server import HTTPServer, BaseHTTPRequestHandler
from urllib.parse import parse_qs, urlparse
import threading

SCOPES = [
    'https://www.googleapis.com/auth/spreadsheets',
    'https://www.googleapis.com/auth/drive'
]

# Load credentials
with open('credentials.json') as f:
    creds_data = json.load(f)

CLIENT_ID = creds_data['installed']['client_id']
CLIENT_SECRET = creds_data['installed']['client_secret']
REDIRECT_URI = 'http://localhost:8080'

def get_credentials():
    """Get credentials via OAuth flow"""
    token_file = 'token.json'
    
    # Try to load existing token
    if os.path.exists(token_file):
        print(f"Loading existing token: {token_file}")
        with open(token_file) as f:
            token = json.load(f)
        creds = Credentials.from_authorized_user_info(token, SCOPES)
        if creds.valid:
            return creds
    
    # Manual OAuth flow
    print("\nStarting OAuth authorization...")
    print(f"Client ID: {CLIENT_ID}")
    
    # Authorization URL
    auth_url = (
        "https://accounts.google.com/o/oauth2/auth"
        f"?client_id={CLIENT_ID}"
        f"&redirect_uri={REDIRECT_URI}"
        f"&response_type=code"
        f"&scope={'%20'.join(SCOPES)}"
        f"&access_type=offline"
    )
    
    print(f"\nOpening browser for authorization...")
    print(f"URL: {auth_url[:100]}...")
    
    # Start local server to receive callback
    auth_code = None
    
    class AuthHandler(BaseHTTPRequestHandler):
        def do_GET(self):
            nonlocal auth_code
            query = parse_qs(urlparse(self.path).query)
            if 'code' in query:
                auth_code = query['code'][0]
                self.send_response(200)
                self.send_header('Content-type', 'text/html')
                self.end_headers()
                self.wfile.write(b"<html><body><h1>Success!</h1><p>You can close this window.</p></body></html>")
            else:
                self.send_response(400)
                self.end_headers()
        
        def log_message(self, format, *args):
            pass
    
    server = HTTPServer(('localhost', 8080), AuthHandler)
    thread = threading.Thread(target=server.serve_forever)
    thread.daemon = True
    thread.start()
    
    # Open browser
    webbrowser.open(auth_url)
    print("Browser opened. Please authorize...")
    
    # Wait for callback (max 5 minutes)
    import time
    for _ in range(300):
        if auth_code:
            break
        time.sleep(1)
    
    server.shutdown()
    
    if not auth_code:
        raise Exception("Authorization timeout")
    
    # Exchange code for token
    import requests
    token_response = requests.post(
        'https://oauth2.googleapis.com/token',
        data={
            'code': auth_code,
            'client_id': CLIENT_ID,
            'client_secret': CLIENT_SECRET,
            'redirect_uri': REDIRECT_URI,
            'grant_type': 'authorization_code'
        }
    )
    
    token = token_response.json()
    
    # Save token
    with open(token_file, 'w') as f:
        json.dump(token, f)
    print(f"Token saved: {token_file}")
    
    return Credentials.from_authorized_user_info(token, SCOPES)

def main():
    print("Vincustom Data Upload Script")
    print("=" * 50)
    
    # Get credentials
    creds = get_credentials()
    print("OAuth successful!")
    
    # Authorize with gspread
    client = gspread.authorize(creds)
    print("Google Sheets authorized!")
    
    # Read CSV
    csv_file = r'C:\Users\mayao2\.openclaw\workspace\01-VALUE-CREATION\market-research\vincustom-products\vincustom-products-full.csv'
    print(f"\nReading CSV: {csv_file}")
    
    with open(csv_file, 'r', encoding='utf-8') as f:
        data = list(csv.reader(f))
    
    print(f"Loaded {len(data)} rows")
    
    # Create spreadsheet
    print("\nCreating Google Sheet...")
    spreadsheet = client.create('Vincustom Products 2026')
    print(f"Created: {spreadsheet.title}")
    
    # Upload data
    print("Uploading data...")
    worksheet = spreadsheet.sheet1
    worksheet.update('A1', data)
    print(f"Uploaded {len(data)} rows to sheet!")
    
    # Share with duongvanngoc.vn@gmail.com
    print("\nSharing with duongvanngoc.vn@gmail.com...")
    spreadsheet.share('duongvanngoc.vn@gmail.com', perm_type='user', role='writer')
    print("Shared!")
    
    # Print URL
    print("\n" + "=" * 50)
    print("SUCCESS!")
    print(f"Google Sheet URL:")
    print(f"   {spreadsheet.url}")
    print("=" * 50)

if __name__ == '__main__':
    main()
