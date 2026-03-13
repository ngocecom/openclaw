# 🎮 MISSION CONTROL DASHBOARD

**Created:** March 13, 2026  
**Version:** 1.0.0  
**Purpose:** Multi-Agent System Visualization & Control

---

## 🖥️ OVERVIEW

Mission Control là hệ thống dashboard trực quan để quản lý, giám sát và tương tác với hệ thống multi-agent OpenClaw.

---

## 📊 SCREEN 1: TEAM SCREEN

### Purpose
Hiển thị sơ đồ tổ chức, status và performance của toàn bộ đội ngũ agents.

### Components

#### 1.1 Org Chart
```
┌─────────────────────────────────────────────┐
│              HENRY (Master)                 │
│         🟢 Online | Qwen 3.5 Plus           │
│         Task: Monitoring subagents          │
└─────────────────────────────────────────────┘
                      │
    ┌─────────────────┼─────────────────┐
    │                 │                 │
┌────────┐      ┌────────┐       ┌────────┐
│RESEARCH│      │ANALYST │       │ASSISTANT│
│  🟡    │      │  ⚪    │       │  🟢     │
│ Busy   │      │ Idle   │       │ Online  │
└────────┘      └────────┘       └────────┘
```

#### 1.2 Agent Cards
Mỗi agent có card hiển thị:
- **Avatar** (pixel art)
- **Name & Role**
- **Status** (🟢 Online, 🟡 Busy, ⚪ Idle, 🔴 Offline)
- **Current Model**
- **Current Task**
- **Progress Bar**
- **Performance Metrics**

#### 1.3 Metrics Panel
```
┌─────────────────────────────────────────────┐
│ TODAY'S METRICS                             │
├─────────────────────────────────────────────┤
│ Tasks Completed:     47                     │
│ Avg Response Time:   23s                    │
│ Total Tokens:        125,430                │
│ Estimated Cost:      $0.87                  │
│ Active Agents:       4/5                    │
└─────────────────────────────────────────────┘
```

### Tech Stack
- **Frontend:** HTML/CSS/JavaScript
- **Real-time:** WebSocket connection to Henry
- **Charts:** Chart.js hoặc D3.js
- **Icons:** Pixel art assets

---

## 📅 SCREEN 2: CALENDAR VIEW

### Purpose
Theo dõi lịch trình, scheduled tasks và cron jobs của toàn bộ hệ thống.

### Components

#### 2.1 Timeline View
```
06:00 ── [ENGINEER] System Health Check ──────
07:00 ─────────────────────────────────────────
08:00 ── [RESEARCHER] Daily News Digest ──────
09:00 ── [ANALYST] Weekly Market Analysis ────
10:00 ── [ASSISTANT] Daily Report ────────────
11:00 ─────────────────────────────────────────
12:00 ─────────────────────────────────────────
...
```

#### 2.2 Cron Jobs List
| Job | Agent | Schedule | Next Run | Status |
|-----|-------|----------|----------|--------|
| Daily News | RESEARCHER | 8:00 AM Daily | Tomorrow 8:00 AM | ✅ Active |
| Health Check | ENGINEER | 6:00 AM Daily | Tomorrow 6:00 AM | ✅ Active |
| Weekly Review | ANALYST | Sunday 8:00 PM | Sunday 8:00 PM | ✅ Active |
| Daily Report | ASSISTANT | 10:00 AM Daily | Tomorrow 10:00 AM | ✅ Active |

#### 2.3 Upcoming Tasks
- **Next 1 hour:** [RESEARCHER] News search for "Iran-US"
- **Next 24 hours:** [ASSISTANT] Daily report generation
- **Next 7 days:** [ANALYST] Weekly performance review

#### 2.4 Drag & Drop Scheduler
- Drag task to reschedule
- Click to edit details
- Color-coded by agent
- Recurring task support

### Tech Stack
- **Calendar Library:** FullCalendar.js
- **Data Source:** Henry's task scheduler
- **Updates:** Real-time via WebSocket

---

## 🏢 SCREEN 3: OFFICE VIEW (Pixel Art 2D)

### Purpose
Giao diện giải trí, gamification, cho phép quan sát agents "làm việc" trong văn phòng ảo.

### Components

#### 3.1 Office Layout
```
┌──────────────────────────────────────────────────────┐
│  OPENCLAW HEADQUARTERS                               │
├──────────────────────────────────────────────────────┤
│                                                      │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐          │
│  │ RESEARCH │  │ ANALYST  │  │ MEETING  │          │
│  │   LAB    │  │  OFFICE  │  │   ROOM   │          │
│  │   🟡     │  │   ⚪     │  │          │          │
│  └──────────┘  └──────────┘  └──────────┘          │
│                                                      │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐          │
│  │ ASSISTANT│  │  WRITER  │  │ENGINEER  │          │
│  │  DESK    │  │  CORNER  │  │  LAB     │          │
│  │   🟢     │  │   ⚪     │  │   🟡     │          │
│  └──────────┘  └──────────┘  └──────────┘          │
│                                                      │
│  ┌──────────────────────────────────────────┐       │
│  │         HENRY (Control Room)             │       │
│  │              🟢                          │       │
│  └──────────────────────────────────────────┘       │
│                                                      │
│  [🍳 Break Room]  [☕ Coffee Machine]               │
│                                                      │
└──────────────────────────────────────────────────────┘
```

#### 3.2 Agent Avatars (Pixel Art)
Mỗi agent có avatar pixel art riêng:
- **Henry:** 👔 Business suit, glasses
- **Researcher:** 📰 Holding newspaper, backpack
- **Analyst:** 📊 Charts, calculator
- **Assistant:** 🤝 Headset, friendly smile
- **Writer:** ✍️ Typing, coffee cup
- **Engineer:** 🔧 Tools, tech gear

#### 3.3 Real-time Activities
Agents di chuyển và hoạt động:
- **Walking** between rooms
- **Working** at desks (animated)
- **Meeting** in conference room
- **Break time** at coffee machine
- **Idle** animation when no tasks

#### 3.4 Interactive Features
- **Click on agent** → Chat window opens
- **Drag task** → Drop on agent to assign
- **Right-click room** → View room details
- **Hover** → Show current activity

#### 3.5 Gamification Elements
- **Level System:** Agents gain XP from completed tasks
- **Achievements:** "100 Tasks Completed", "Fast Responder", etc.
- **Leaderboard:** Top performer this week
- **Unlockables:** New office decorations, avatar skins

### Tech Stack
- **Game Engine:** Phaser.js hoặc Pixi.js
- **Assets:** Custom pixel art (16x16 or 32x32)
- **Animation:** Sprite sheets
- **Multiplayer:** Socket.io for real-time updates

---

## 🔧 TECHNICAL ARCHITECTURE

### Backend (Henry)
```python
# WebSocket Server
class MissionControlServer:
    def __init__(self):
        self.agents = {}
        self.tasks = {}
        self.clients = []
    
    def broadcast_status(self):
        # Send agent status to all connected clients
        pass
    
    def handle_task_update(self, task):
        # Update task progress
        pass
```

### Frontend
```javascript
// WebSocket Connection
const ws = new WebSocket('ws://localhost:8765');

ws.onmessage = (event) => {
    const data = JSON.parse(event.data);
    updateTeamScreen(data.agents);
    updateCalendar(data.tasks);
    updateOfficeView(data.agents);
};
```

### Data Flow
```
Henry (Orchestrator)
    ↓ WebSocket
Mission Control Server
    ↓ WebSocket
Frontend Dashboard
    ↓
Team Screen | Calendar View | Office View
```

---

## 📱 RESPONSIVE DESIGN

### Desktop (1920x1080)
- Full 3-screen layout
- All features enabled
- Real-time updates

### Tablet (1024x768)
- Tabbed interface (switch between screens)
- Simplified animations
- Touch-friendly controls

### Mobile (375x667)
- Single column layout
- Priority: Team Screen > Calendar > Office
- Reduced animations for performance

---

## 🎨 DESIGN SYSTEM

### Color Palette
```
Primary:   #4A90E2 (Blue - Henry)
Secondary: #7B68EE (Purple - Analyst)
Success:   #50C878 (Green - Online)
Warning:   #FFD700 (Yellow - Busy)
Error:     #FF6B6B (Red - Offline)
Idle:      #B0B0B0 (Gray - Idle)
```

### Typography
- **Headers:** 'Press Start 2P' (pixel art font)
- **Body:** 'Inter' or 'Roboto' (clean sans-serif)
- **Code:** 'Fira Code' (monospace)

### Icons
- Pixel art style (16x16, 32x32)
- Consistent theme across all screens
- Animated sprites for agents

---

## 🚀 IMPLEMENTATION ROADMAP

### Week 1: Core Dashboard
- [ ] Setup WebSocket server in Henry
- [ ] Create basic HTML/CSS layout
- [ ] Implement Team Screen
- [ ] Real-time status updates

### Week 2: Calendar & Scheduling
- [ ] Integrate FullCalendar.js
- [ ] Connect to Henry's task scheduler
- [ ] Drag-and-drop functionality
- [ ] Cron job visualization

### Week 3: Office View
- [ ] Setup Phaser.js game engine
- [ ] Create office layout
- [ ] Design pixel art avatars
- [ ] Implement movement animation

### Week 4: Polish & Features
- [ ] Gamification (XP, achievements)
- [ ] Interactive features (click to chat)
- [ ] Mobile responsive design
- [ ] Performance optimization

---

## 📊 SUCCESS METRICS

| Metric | Target | Measurement |
|--------|--------|-------------|
| Dashboard Load Time | <2 seconds | Page load speed |
| Real-time Update Latency | <100ms | WebSocket ping |
| User Engagement | >10 min/session | Time spent on dashboard |
| Task Assignment via Dashboard | >20% | Tasks assigned via drag-drop |
| User Satisfaction | >9/10 | User feedback |

---

## 🔗 INTEGRATION POINTS

### With Henry
- WebSocket for real-time updates
- REST API for historical data
- GraphQL for complex queries (optional)

### With Subagents
- Status heartbeat (every 10 seconds)
- Task progress updates
- Completion notifications

### With External Services
- Google Calendar sync (optional)
- Slack/Discord notifications
- Email digests

---

## 📁 FILE STRUCTURE

```
workspace/
└── 01-VALUE-CREATION/
    └── multi-agent-system/
        └── mission-control/
            ├── backend/
            │   ├── server.py
            │   ├── websocket_handler.py
            │   └── api.py
            ├── frontend/
            │   ├── index.html
            │   ├── css/
            │   │   ├── team-screen.css
            │   │   ├── calendar-view.css
            │   │   └── office-view.css
            │   ├── js/
            │   │   ├── team-screen.js
            │   │   ├── calendar-view.js
            │   │   └── office-view.js
            │   └── assets/
            │       ├── pixel-art/
            │       │   ├── henry.png
            │       │   ├── researcher.png
            │       │   └── ...
            │       └── icons/
            └── docs/
                └── MISSION-CONTROL-SPEC.md
```

---

*Created: March 13, 2026*  
*Version: 1.0.0*  
*Status: Design Phase*
