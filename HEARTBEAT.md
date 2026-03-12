# HEARTBEAT.md - Periodic Tasks

**Purpose:** Tasks to check/run automatically every 30 minutes

**Auto-Approval:** ENABLED (user approved 2026-03-12)
**Daily Report Time:** 10:00 AM Vietnam Time (GMT+7)
**Failover Policy:** Report immediately upon reconnection if scheduled report was missed

---

## 🚨 FAILOVER POLICY

### If Connection Lost at Report Time:
1. **Mark report as MISSED** in `memory/commit-history.md`
2. **Retry every 30 minutes** until successful
3. **Send report IMMEDIATELY** when connection restored
4. **Note the delay** in report header (e.g., "Scheduled: 10:00 AM, Sent: 11:30 AM")

### Connection Check:
- Check gateway status every heartbeat (30 min)
- If gateway unreachable → mark as OFFLINE
- When gateway back → trigger MISSED reports immediately

### Missed Report Tracking:
```yaml
missed_reports:
  max_retries: unlimited
  retry_interval: 30 minutes
  notify_on_reconnect: true
  include_delay_reason: true
```

---

## 🔄 PERIODIC CHECKS

### Every Session (Automatic)
- [x] Read MEMORY.md for context
- [x] Read today's daily log (memory/YYYY-MM-DD.md)
- [x] Check for pending tasks from previous session

### Every 30 Minutes (Heartbeat)
- [ ] Check Telegram for new messages
- [ ] Monitor gateway status
- [ ] Review browser tabs (if any open)
- [ ] Check for urgent news (Iran-US conflict)
- [ ] Auto-commit changes if files modified (APPROVED)

### Daily (10:00 AM Vietnam Time - Report)
- [ ] Generate daily report summary
- [ ] Send report via Telegram at 10:00 AM
- [ ] Update daily log (memory/YYYY-MM-DD.md)
- [ ] Auto-commit changes (APPROVED - no need to ask)
- [ ] Note pending tasks for tomorrow
- [ ] Check project progress

### Weekly (Every 7 Days - Sunday 20:00)
- [ ] Security audit (`openclaw security audit`)
- [ ] Review and consolidate MEMORY.md
- [ ] Update work-projects.md status
- [ ] Clean up old files
- [ ] Check for OpenClaw updates
- [ ] Auto-commit weekly summary (APPROVED)

---

## 📋 TASK QUEUE

### High Priority
1. Complete Cốc Cốc installation (user action required)
2. Update OpenClaw to v2026.3.11
3. Configure Brave API key

### Medium Priority
4. Setup browser automation workflows
5. Create custom skills for common tasks
6. Expand memory system

### Low Priority
7. Explore additional channels (Discord, WhatsApp)
8. Setup sub-agents for specialized tasks
9. Create documentation

---

## 🎯 CURRENT FOCUS

**Primary:** OpenClaw Productivity Setup (60% complete)
**Secondary:** Information Monitoring (active)
**Tertiary:** Browser Automation (50% complete)

---

## 📝 NOTES FOR NEXT HEARTBEAT

- User prefers Vietnamese responses
- Be proactive, don't ask excessive questions
- Always write important info to files
- Check if Cốc Cốc installation completed

### 🎯 PERSONAL MBA ALIGNMENT CHECK

**Before any significant action, verify:**
1. Does this create value? (Value Creation)
2. Is this clear to user? (Marketing)
3. Is this easy to use? (Sales)
4. Can I deliver this reliably? (Value Delivery)
5. Is this efficient? (Finance)

**Red Flags (stop and reassess):**
- Building without validating need
- Complex solutions when simple works
- Making assumptions without checking
- Wasting resources on low-value tasks

**Reference:** `memory/OPENCLAW-PRINCIPLES.md` (operating constitution)

---

*Update this file when new periodic tasks are identified*
