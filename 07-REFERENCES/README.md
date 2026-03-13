# 📚 REFERENCES

**Purpose:** Supporting materials

---

## 📁 FOLDER STRUCTURE

```
07-REFERENCES/
├── books/                # Book notes, summaries
│   └── personal-mba-summary.md
├── articles/             # Saved articles, research
├── tools/                # Tool documentation, configs
│   ├── openclaw-commands.md
│   └── cliproxy-guide.md
└── templates/            # Reusable templates, scripts
    ├── daily-report-template.md
    └── weekly-review-template.md
```

---

## 📖 BOOKS & FRAMEWORKS

### The Personal MBA (Josh Kaufman)
**Applied to OpenClaw:** March 13, 2026

**5 Functions of Business:**
1. **Value Creation** - Solve real problems
2. **Marketing** - Communicate clearly
3. **Sales** - Reduce friction
4. **Value Delivery** - Deliver reliably
5. **Finance** - Use resources wisely

**Key Concepts Applied:**
- 80/20 Rule (Pareto Principle)
- Opportunity Cost
- Sunk Cost Fallacy
- Diminishing Returns
- Feedback Loops

**File:** `personal-mba-framework.md`

---

## 🛠️ TOOLS DOCUMENTATION

### OpenClaw Commands
```bash
# Status checks
openclaw status
openclaw status --deep
openclaw gateway status

# Gateway management
openclaw gateway start
openclaw gateway stop
openclaw gateway restart

# Updates
openclaw update
openclaw doctor --non-interactive

# Security
openclaw security audit
openclaw security audit --deep

# Memory
openclaw memory status
openclaw memory status --deep

# Pairing (Telegram)
openclaw pairing list telegram
openclaw pairing approve telegram <CODE>
```

### CLIProxy Setup
- **Location:** `C:\Users\mayao2\.openclaw\cliproxy\`
- **Port:** 1234
- **APIs:** DashScope, OpenRouter
- **Models:** 7 available

### Browser Automation
- **Profile:** `openclaw` (isolated Chrome)
- **Port:** 18800 (CDP)
- **User Data:** `~/.openclaw/browser/openclaw/user-data`

---

## 📝 TEMPLATES

### Daily Report Template
```markdown
# Daily Report - YYYY-MM-DD

## ✅ Accomplishments
- Task 1
- Task 2

## ⏳ Pending
- Task 1
- Task 2

## 📊 Metrics
- Tasks completed: X
- Time saved: Y hours

## 📝 Notes
- Any observations
```

### Weekly Review Template
```markdown
# Weekly Review - Week of YYYY-MM-DD

## 🎯 Goals Progress
- Goal 1: [Status]
- Goal 2: [Status]

## 📈 Wins
- Win 1
- Win 2

## 📉 Challenges
- Challenge 1
- Challenge 2

## 🔄 Changes for Next Week
- Change 1
- Change 2
```

---

## 🔗 EXTERNAL RESOURCES

### Documentation
- OpenClaw Docs: https://docs.openclaw.ai
- OpenClaw GitHub: https://github.com/openclaw/openclaw
- Community Discord: https://discord.com/invite/clawd

### APIs
- DashScope: https://dashscope.aliyun.com
- OpenRouter: https://openrouter.ai
- Brave Search: https://brave.com/search/api

### Tools
- Google Drive: https://drive.google.com
- Telegram Bot API: https://core.telegram.org/bots/api

---

## 📊 METRICS TO TRACK

- Documentation completeness
- Template usage rate
- External resource clicks
- Knowledge base growth

---

*Last Updated: March 13, 2026*
