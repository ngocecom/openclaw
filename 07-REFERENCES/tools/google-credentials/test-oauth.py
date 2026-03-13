#!/usr/bin/env python3
"""Simple OAuth test script"""

import json
import webbrowser
import requests
from http.server import HTTPServer, BaseHTTPRequestHandler
from urllib.parse import parse_qs, urlparse
import threading

# Load credentials
with open('credentials.json') as f:
    creds = json.load(f)

CLIENT_ID = creds['installed']['client_id']
CLIENT_SECRET = creds['installed']['client_secret']
REDIRECT_URI = 'http://localhost:8080'

SCOPES = 'https://www.googleapis.com/auth/spreadsheets https://www.googleapis.com/auth/drive'

print("=" * 60)
print("GOOGLE OAUTH TEST")
print("=" * 60)

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

print(f"\n1. Opening browser...")
print(f"URL: {auth_url[:100]}...")

# Start local server
auth_code = None

class Handler(BaseHTTPRequestHandler):
    def do_GET(self):
        global auth_code
        query = parse_qs(urlparse(self.path).query)
        if 'code' in query:
            auth_code = query['code'][0]
            self.send_response(200)
            self.send_header('Content-type', 'text/html')
            self.end_headers()
            self.wfile.write(b"<html><body><h1>Success! You can close this window.</h1></body></html>")
        else:
            self.send_response(400)
            self.end_headers()
    
    def log_message(self, format, *args):
        pass

server = HTTPServer(('localhost', 8080), Handler)
thread = threading.Thread(target=server.serve_forever)
thread.daemon = True
thread.start()

# Open browser
webbrowser.open(auth_url)

print("\n2. Please authorize in browser...")
print("   Login with: fachuhomes@gmail.com")
print("   Click: Allow/Continue")

# Wait for code (max 5 minutes)
import time
for i in range(300):
    if auth_code:
        break
    time.sleep(1)

server.shutdown()

if not auth_code:
    print("\n❌ Timeout! No authorization code received.")
    exit(1)

print(f"\n3. Received code: {auth_code[:20]}...")

# Exchange for token
print("\n4. Exchanging code for token...")
token_resp = requests.post(
    'https://oauth2.googleapis.com/token',
    data={
        'code': auth_code,
        'client_id': CLIENT_ID,
        'client_secret': CLIENT_SECRET,
        'redirect_uri': REDIRECT_URI,
        'grant_type': 'authorization_code'
    }
)

token = token_resp.json()

if 'access_token' not in token:
    print(f"\n❌ Error getting token: {token}")
    exit(1)

# Save token
with open('token.json', 'w') as f:
    json.dump(token, f)

print(f"\n✅ SUCCESS! Token saved to token.json")
print(f"   Access token: {token.get('access_token', '')[:20]}...")
print(f"   Expires in: {token.get('expires_in', 'N/A')} seconds")

# Test Drive API
print("\n5. Testing Google Drive API...")
headers = {'Authorization': f"Bearer {token['access_token']}"}
resp = requests.get(
    'https://www.googleapis.com/drive/v3/files?pageSize=5',
    headers=headers
)

if resp.status_code == 200:
    files = resp.json().get('files', [])
    print(f"\n✅ Drive API works! Found {len(files)} files:")
    for f in files[:5]:
        print(f"   - {f['name']}")
else:
    print(f"\n❌ Drive API error: {resp.status_code} - {resp.text}")

print("\n" + "=" * 60)
print("TEST COMPLETE!")
print("=" * 60)
