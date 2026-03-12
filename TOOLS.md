# TOOLS.md - Local Setup Notes

**Purpose:** Environment-specific configuration and shortcuts

---

## 🔧 OPENCLAW SETUP

### Current Configuration
```
Version: 2026.3.8
Update Available: 2026.3.11
Gateway: ws://127.0.0.1:18789 (38ms latency)
Model: qwen3.5-plus (200k context)
```

### Useful Commands
```bash
# Status check
openclaw status
openclaw status --deep

# Gateway management
openclaw gateway status
openclaw gateway stop
openclaw gateway start
openclaw gateway restart

# Updates
openclaw update
openclaw doctor --non-interactive

# Security
openclaw security audit
openclaw security audit --deep

# Logs
openclaw logs --follow
openclaw logs --last 100

# Pairing (Telegram)
openclaw pairing list telegram
openclaw pairing approve telegram <CODE>

# Memory
openclaw memory status
openclaw memory status --deep
```

---

## 📱 TELEGRAM BOT

### Bot Details
- **Name:** @Openclaw_vps2_bot
- **Token:** 8709547130:AAH-TFsHPeOSC42wwbu_3SvwmEPA3ofNm_4
- **Status:** ON, OK
- **Paired User:** Ngoc (@ngocecom, ID: 7578098687)

### Configuration
```json
{
  "channels": {
    "telegram": {
      "enabled": true,
      "botToken": "8709...Nm_4",
      "dmPolicy": "pairing",
      "groupPolicy": "allowlist",
      "groups": {
        "*": {
          "requireMention": true
        }
      }
    }
  }
}
```

### Commands
- `/start` - Start bot
- `/help` - Get help
- `/status` - Check system status
- `/activation` - Toggle activation mode

---

## 🌐 BROWSER AUTOMATION

### Profiles
- **openclaw** - Isolated Chrome instance (recommended)
  - Port: 18800 (CDP)
  - User Data: `~/.openclaw/browser/openclaw/user-data`
  - Status: Working

- **chrome** - Chrome extension relay
  - Requires: Manual tab attach (click extension icon)
  - Status: Available but needs setup

### Common Patterns
```javascript
// Open URL
browser(action="open", profile="openclaw", url="https://example.com")

// Navigate
browser(action="navigate", profile="openclaw", targetId="<id>", url="https://...")

// Snapshot
browser(action="snapshot", profile="openclaw", targetId="<id>")

// Click
browser(action="act", kind="click", ref="e12", targetId="<id>")
```

### Tested Sites
- ✅ VnExpress.net
- ✅ Gmail.com
- ✅ Coccoc.com

---

## 📁 FILE STRUCTURE

```
C:\Users\mayao2\.openclaw\workspace\
├── MEMORY.md              # Long-term memory
├── USER.md                # User profile
├── SOUL.md                # Agent personality
├── HEARTBEAT.md           # Periodic tasks
├── TOOLS.md               # This file
├── memory/                # Daily logs
│   ├── 2026-03-12.md
│   └── work-projects.md
└── [projects/]            # Future project files
```

---

## 🔑 API KEYS STATUS

| Service | Status | Config Location |
|---------|--------|-----------------|
| Telegram Bot | ✅ Configured | channels.telegram.botToken |
| Brave Search | ❌ Missing | Need: `openclaw configure --section web` |
| OpenAI | ❌ Not set | Optional for embeddings |
| Google/Gemini | ❌ Not set | Optional alternative |

### To Add Brave API Key
```bash
openclaw configure --section web
# Or set env: BRAVE_API_KEY=your_key_here
```

---

## 🛠️ TROUBLESHOOTING

### Common Issues

**1. Update Fails (EBUSY)**
```
Error: npm error code EBUSY
Fix: Stop gateway first
  openclaw gateway stop
  openclaw update
  openclaw gateway start
```

**2. Browser Not Found**
```
Error: tab not found
Fix: Use correct profile
  - "openclaw" for isolated browser
  - "chrome" for extension relay (needs manual attach)
```

**3. Web Search Fails**
```
Error: missing_brave_api_key
Fix: Configure API key or use web_fetch workaround
```

**4. Software Install Blocked**
```
Error: UAC prompt
Fix: Manual installation required (Windows security)
```

**5. Telegram Not Receiving**
```
Check: openclaw pairing list telegram
Fix: Approve pairing code if pending
```

---

## 📊 PERFORMANCE NOTES

### Latency
- Gateway: 38ms (excellent)
- Telegram: <1 minute response
- Browser: 1-3 seconds per action

### Token Usage
- Model: qwen3.5-plus
- Context: 200k tokens
- Usage: Tracked in session status

### Storage
- Workspace: `C:\Users\mayao2\.openclaw\workspace`
- Memory files: Auto-commit recommended
- Browser data: `~/.openclaw/browser/`

---

## 🔐 SECURITY NOTES

### Current Status
- Security Audit: 0 critical, 2 warnings
- Warnings:
  1. Reverse proxy headers not trusted (not applicable for local-only)
  2. Multi-user setup potential (single user currently)

### Best Practices
- Keep API keys in config, not in files
- Review `openclaw security audit` monthly
- Use workspace-only file access
- Don't share pairing codes publicly

---

*Update this file when new tools or configurations are added*
