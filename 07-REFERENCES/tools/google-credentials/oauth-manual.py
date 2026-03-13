#!/usr/bin/env python3
"""OAuth with manual code paste - 100% reliable"""

import json
import requests
import webbrowser

# Load credentials
with open('credentials.json') as f:
    creds = json.load(f)

CLIENT_ID = creds['installed']['client_id']
CLIENT_SECRET = creds['installed']['client_secret']
REDIRECT_URI = 'urn:ietf:wg:oauth:2.0:oob'

SCOPES = 'https://www.googleapis.com/auth/spreadsheets https://www.googleapis.com/auth/drive'

print("=" * 70)
print("GOOGLE OAUTH - MANUAL CODE PASTE")
print("=" * 70)

# Build auth URL
auth_url = (
    f"https://accounts.google.com/o/oauth2/auth?"
    f"client_id={CLIENT_ID}&"
    f"redirect_uri={REDIRECT_URI}&"
    f"response_type=code&"
    f"scope={requests.utils.quote(SCOPES)}&"
    f"access_type=offline&"
    f"prompt=consent"
)

print("\nSTEP 1: Open this URL in your browser:")
print("-" * 70)
print(auth_url)
print("-" * 70)

# Open browser
webbrowser.open(auth_url)

print("\nSTEP 2: Follow these steps:")
print("   1. Login with: fachuhomes@gmail.com")
print("   2. Click 'Advanced' if you see warning")
print("   3. Click 'Go to OpenClaw Automation (unsafe)'")
print("   4. Click 'Allow'")
print("   5. COPY the authorization code from browser")

print("\nSTEP 3: Paste the code below:")
print("-" * 70)

code = input("Authorization code: ").strip()

if not code:
    print("\nERROR: No code provided!")
    exit(1)

print(f"\nSTEP 4: Exchanging code for token...")

# Exchange for token
token_resp = requests.post(
    'https://oauth2.googleapis.com/token',
    data={
        'code': code,
        'client_id': CLIENT_ID,
        'client_secret': CLIENT_SECRET,
        'redirect_uri': REDIRECT_URI,
        'grant_type': 'authorization_code'
    }
)

token = token_resp.json()

if 'access_token' not in token:
    print(f"\nERROR: {token}")
    exit(1)

# Save token
with open('token.json', 'w') as f:
    json.dump(token, f)

print(f"\nSUCCESS! Token saved to token.json")

# Test Drive API
print("\nSTEP 5: Testing Google Drive API...")
headers = {'Authorization': f"Bearer {token['access_token']}"}
resp = requests.get(
    'https://www.googleapis.com/drive/v3/files?pageSize=5',
    headers=headers
)

if resp.status_code == 200:
    files = resp.json().get('files', [])
    print(f"\nDrive API works! Found {len(files)} files:")
    for f in files[:5]:
        print(f"   - {f['name']}")
else:
    print(f"\nDrive API error: {resp.status_code}")

print("\n" + "=" * 70)
print("NOW RUN: python upload-vincustom.py")
print("=" * 70)
