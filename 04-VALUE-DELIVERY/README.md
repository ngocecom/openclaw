# 📦 VALUE DELIVERY

**Purpose:** How do we deliver reliably?

---

## 📁 FOLDER STRUCTURE

```
04-VALUE-DELIVERY/
├── systems/              # Architecture, workflows, automation
│   ├── google-sheet-info.md
│   └── cliproxy-config/
├── processes/            # SOPs, checklists, playbooks
│   └── HEARTBEAT.md
├── quality/              # Testing, monitoring, alerts
└── support/              # Troubleshooting, FAQs, tickets
```

---

## 🎯 KEY QUESTIONS

1. How do we ensure reliability?
2. What's our error handling strategy?
3. How do we monitor system health?
4. What's our support process?

---

## ⚙️ SYSTEMS

### Core Architecture
```
User → Telegram → OpenClaw Gateway → Model API
                              ↓
                      File System, Browser, Memory
```

### Key Components
| Component | Status | Responsibility |
|-----------|--------|----------------|
| **Gateway** | ✅ Running (18789) | WebSocket server, RPC |
| **CLIProxy** | ✅ Running (1234) | Multi-API router |
| **Browser** | ✅ OpenClaw profile | Web automation |
| **Memory** | ✅ File-based | Knowledge storage |
| **Telegram Bot** | ✅ @Openclaw_vps2_bot | Primary interface |

### API Providers
- **DashScope (Alibaba):** Qwen 3.5 Plus (default)
- **OpenRouter:** Qwen 2.5, Claude 3.5, GPT-4
- **CLIProxy:** Unified interface for all

---

## 📋 PROCESSES

### Heartbeat Process
**Frequency:** Every 30 minutes  
**Tasks:**
1. Check gateway status
2. Monitor Telegram messages
3. Review pending tasks
4. Auto-commit changes (if approved)

### Daily Report Process
**Time:** 10:00 AM Vietnam Time  
**Content:**
- Yesterday's accomplishments
- Pending tasks
- Project status updates
- Metrics summary

### Weekly Review Process
**Time:** Sunday 20:00 VN Time  
**Tasks:**
1. Review files created this week
2. Categorize by 5 functions
3. Update MEMORY.md
4. Plan next week's priorities

---

## ✅ QUALITY ASSURANCE

### Health Checks
- Gateway latency (<100ms)
- API response time (<5s)
- Browser automation success rate (>95%)
- Message delivery rate (>99%)

### Monitoring
- `openclaw status --deep` - System health
- `openclaw gateway status` - Gateway status
- `openclaw memory status` - Memory indexing
- Process monitoring (CLIProxy, Gateway)

### Alerts
- Gateway down >5 min
- API failures >3 consecutive
- Browser automation errors
- Memory sync failures

---

## 🆘 SUPPORT

### Troubleshooting Guide
| Issue | Solution |
|-------|----------|
| Gateway won't start | `openclaw gateway restart` |
| Telegram not receiving | Check pairing: `openclaw pairing list telegram` |
| Browser not found | Use `profile="openclaw"` not `"chrome"` |
| Web search fails | Add Brave API key |
| Memory not loading | Check file paths, restart session |

### FAQ
1. **How to change model?** → `/session_status model=<model-name>`
2. **How to switch API?** → Use CLIProxy or `switch-api.ps1` script
3. **Where are files stored?** → `C:\Users\mayao2\.openclaw\workspace\`
4. **How to backup?** → Sync workspace to Google Drive

---

## 📊 METRICS TO TRACK

- Uptime percentage
- Average response time
- Error rate
- Support ticket resolution time

---

*Last Updated: March 13, 2026*
