#!/usr/bin/env python3
"""Generate OAuth URL for manual authorization"""

import json
import urllib.parse

with open('credentials.json') as f:
    creds_data = json.load(f)

CLIENT_ID = creds_data['installed']['client_id']
SCOPES = [
    'https://www.googleapis.com/auth/spreadsheets',
    'https://www.googleapis.com/auth/drive'
]

auth_url = (
    "https://accounts.google.com/o/oauth2/auth"
    f"?client_id={CLIENT_ID}"
    f"&redirect_uri=urn:ietf:wg:oauth:2.0:oob"
    f"&response_type=code"
    f"&scope={urllib.parse.quote(' '.join(SCOPES))}"
    f"&access_type=offline"
    f"&prompt=consent"
)

print("=" * 80)
print("MANUAL OAUTH AUTHORIZATION")
print("=" * 80)
print("\n1. Copy URL below và paste vào browser:")
print(auth_url)
print("\n2. Login với: fachuhomes@gmail.com")
print("\n3. Click 'Allow' hoặc 'Cho phép'")
print("\n4. Copy authorization code từ browser")
print("\n5. Paste code vào terminal này")
print("\n" + "=" * 80)

code = input("Paste authorization code here: ").strip()

if code:
    # Exchange code for token
    import requests
    
    token_response = requests.post(
        'https://oauth2.googleapis.com/token',
        data={
            'code': code,
            'client_id': CLIENT_ID,
            'client_secret': creds_data['installed']['client_secret'],
            'redirect_uri': 'urn:ietf:wg:oauth:2.0:oob',
            'grant_type': 'authorization_code'
        }
    )
    
    token = token_response.json()
    
    if 'access_token' in token:
        # Save token
        with open('token.json', 'w') as f:
            json.dump(token, f)
        
        print("\n✅ Token saved successfully!")
        print("Now run: python upload-vincustom.py")
    else:
        print(f"\n❌ Error: {token}")
else:
    print("\n❌ No code provided")
