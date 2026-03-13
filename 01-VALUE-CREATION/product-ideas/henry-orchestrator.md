# 🤖 HENRY - Master Orchestrator

**Created:** March 13, 2026  
**Version:** 1.0.0  
**Role:** Multi-Agent System Orchestrator

---

## 🎯 OVERVIEW

Henry là agent chính (master orchestrator) của hệ thống OpenClaw multi-agent, chịu trách nhiệm:

1. **Phân việc tự động** - Chia task lớn thành subtasks cho subagents
2. **Giám sát tiến độ** - Theo dõi status của tất cả subagents
3. **Tối ưu chi phí** - Chọn model phù hợp cho từng task
4. **Báo cáo tổng hợp** - Consolidate outputs từ subagents
5. **Mission Control** - Dashboard quản lý trực quan

---

## 👥 TEAM STRUCTURE

### Henry (Master Orchestrator)
- **Model:** Qwen 3.5 Plus (DashScope)
- **Role:** Decision maker, task router
- **Status:** Always on
- **Responsibilities:**
  - Receive user requests
  - Analyze task requirements
  - Assign to appropriate subagent
  - Monitor progress
  - Consolidate results
  - Report to user

---

### Subagents

#### 1. RESEARCHER 📰
- **Model:** Qwen 2.5 72B (OpenRouter) - $0.0007/1k tokens
- **Role:** Information gathering, web research
- **Triggers:**
  - "Tìm kiếm tin tức về..."
  - "Research về..."
  - "Monitor..."
- **Scheduled Tasks:**
  - Daily news digest (8:00 AM VN)
  - Weekly market analysis (Monday 9:00 AM)
- **Output Format:**
  ```markdown
  ## Research Report: [Topic]
  ### Sources
  - [Source 1](url)
  - [Source 2](url)
  
  ### Key Findings
  1. Finding 1
  2. Finding 2
  
  ### Summary
  Brief summary
  ```

#### 2. ANALYST 📊
- **Model:** Claude 3.5 Sonnet (OpenRouter) - $0.003/1k input, $0.015/1k output
- **Role:** Data analysis, insights generation
- **Triggers:**
  - "Phân tích..."
  - "So sánh..."
  - "Đánh giá hiệu quả..."
- **Scheduled Tasks:**
  - Weekly performance review (Sunday 8:00 PM)
  - Monthly cost analysis (1st of month)
- **Output Format:**
  ```markdown
  ## Analysis: [Topic]
  
  ### Data Overview
  | Metric | Value |
  |--------|-------|
  | X | Y |
  
  ### Insights
  - Insight 1
  - Insight 2
  
  ### Recommendations
  1. Recommendation 1
  2. Recommendation 2
  ```

#### 3. ASSISTANT 🤝
- **Model:** Qwen 3.5 Plus (DashScope) - Default
- **Role:** Daily tasks, Telegram communication
- **Triggers:**
  - All Telegram messages
  - Daily commands
  - Quick questions
- **Scheduled Tasks:**
  - Heartbeat checks (every 30 min)
  - Daily report (10:00 AM VN)
- **Output Format:** Direct responses via Telegram

#### 4. WRITER 📝
- **Model:** GPT-4 Turbo (OpenRouter) - $0.01/1k input, $0.03/1k output
- **Role:** Content creation, documentation
- **Triggers:**
  - "Viết báo cáo..."
  - "Tạo documentation..."
  - "Soạn email..."
- **Scheduled Tasks:**
  - Weekly summary (Friday 5:00 PM)
- **Output Format:** Markdown documents, articles

#### 5. ENGINEER 🔧
- **Model:** Qwen 2.5 72B (OpenRouter)
- **Role:** Code generation, automation, debugging
- **Triggers:**
  - "Tạo script..."
  - "Fix lỗi..."
  - "Automate..."
- **Scheduled Tasks:**
  - System health check (daily 6:00 AM)
- **Output Format:** Code files, scripts, technical docs

---

## 🔄 TASK ROUTING LOGIC

Henry sử dụng decision tree để phân việc:

```
User Request
    ↓
Analyze Intent
    ↓
┌─────────────────────────────────┐
│ Task Type?                      │
├─────────────────────────────────┤
│ Research → RESEARCHER           │
│ Analysis → ANALYST              │
│ Communication → ASSISTANT       │
│ Content Creation → WRITER       │
│ Technical → ENGINEER            │
│ Complex → Split & Distribute    │
└─────────────────────────────────┘
    ↓
Select Model (cost optimization)
    ↓
Assign Task
    ↓
Monitor Progress
    ↓
Consolidate Results
    ↓
Report to User
```

---

## 💰 COST OPTIMIZATION

Henry tối ưu chi phí bằng cách:

| Task Complexity | Model Selection | Cost Range |
|----------------|-----------------|------------|
| Simple Q&A | Qwen 3.5 Plus (DashScope) | Free tier |
| Research | Qwen 2.5 72B (OpenRouter) | $0.0007/1k |
| Analysis | Claude 3.5 Sonnet | $0.003-0.015/1k |
| Creative Writing | GPT-4 Turbo | $0.01-0.03/1k |
| Code/Technical | Qwen 2.5 72B | $0.0007/1k |

**Monthly Budget Target:** $30-50

---

## 📊 MISSION CONTROL DASHBOARD

### Architecture
```
┌─────────────────────────────────────────────┐
│         MISSION CONTROL DASHBOARD           │
├─────────────────────────────────────────────┤
│  ┌─────────────┬─────────────┬───────────┐ │
│  │   TEAM      │  CALENDAR   │  OFFICE   │ │
│  │   SCREEN    │    VIEW     │   VIEW    │ │
│  │             │             │           │ │
│  │ - Org Chart │ - Timeline  │ - 2D Pixel│ │
│  │ - Status    │ - Cron Jobs │ - Real-time│ │
│  │ - Metrics   │ - Schedule  │ - Interactive││
│  └─────────────┴─────────────┴───────────┘ │
└─────────────────────────────────────────────┘
```

### Team Screen
- Org chart visualization
- Agent status (🟢 Online, 🟡 Busy, ⚪ Idle, 🔴 Offline)
- Current task per agent
- Performance metrics (tasks completed, avg response time)
- Cost tracking (daily/weekly/monthly)

### Calendar View
- Timeline of scheduled tasks
- Cron job visualization
- Upcoming deadlines
- Agent availability calendar
- Drag-and-drop rescheduling

### Office View (Pixel Art 2D)
- Virtual office layout
- Agent avatars (pixel art)
- Real-time movement
- Click to interact
- Gamification (achievements, levels)

---

## 🛠️ IMPLEMENTATION PLAN

### Phase 1: Core Orchestrator (Week 1)
- [ ] Create Henry agent
- [ ] Implement task routing logic
- [ ] Setup subagent spawning
- [ ] Basic status tracking

### Phase 2: Mission Control (Week 2)
- [ ] Team Screen (web dashboard)
- [ ] Real-time status updates
- [ ] Calendar integration
- [ ] Basic Office View

### Phase 3: Optimization (Week 3)
- [ ] Cost tracking & optimization
- [ ] Performance analytics
- [ ] Advanced scheduling
- [ ] Error handling & recovery

### Phase 4: Polish (Week 4)
- [ ] UI/UX improvements
- [ ] Pixel art assets
- [ ] Notifications & alerts
- [ ] Documentation

---

## 📁 FILE STRUCTURE

```
workspace/
├── 01-VALUE-CREATION/
│   └── multi-agent-system/
│       ├── henry/
│       │   ├── orchestrator.py
│       │   ├── task-router.py
│       │   └── config.json
│       ├── subagents/
│       │   ├── researcher/
│       │   ├── analyst/
│       │   ├── assistant/
│       │   ├── writer/
│       │   └── engineer/
│       └── mission-control/
│           ├── dashboard.html
│           ├── team-screen.js
│           ├── calendar-view.js
│           └── office-view.js
└── 06-HUMAN-SYSTEMS/
    └── agent-protocols.md
```

---

## 🎯 SUCCESS METRICS

| Metric | Target | Measurement |
|--------|--------|-------------|
| Task Completion Rate | >95% | Tasks completed / Tasks assigned |
| Avg Response Time | <30 seconds | User request → Response |
| Cost Efficiency | <$50/month | Total API costs |
| User Satisfaction | >9/10 | User feedback |
| System Uptime | >99% | Henry availability |

---

## 🚀 GETTING STARTED

### For Users:
1. Talk to Henry via Telegram
2. Henry will route tasks automatically
3. Access Mission Control via web dashboard

### For Developers:
1. Review this document
2. Implement Henry orchestrator
3. Create subagent templates
4. Build Mission Control dashboard

---

*Created: March 13, 2026*  
*Version: 1.0.0*  
*Status: Design Phase*
