# Memory Review Guide

**Purpose:** Hướng dẫn review và cập nhật memory thông qua conversation với OpenClaw

---

## 📖 CÁCH REVIEW MEMORY

### **Phương pháp:**
Trao đổi trực tiếp với OpenClaw về thông tin đã lưu trữ để:
- ✅ Xác nhận thông tin còn đúng
- ✅ Cập nhật thông tin thay đổi
- ✅ Xóa thông tin không còn phù hợp
- ✅ Bổ sung thông tin mới

---

## 🎯 CÁC CHỦ ĐỀ CÓ THỂ REVIEW

### 1. **USER PROFILE**
**File:** `USER.md`

**Câu hỏi gợi ý:**
- "Thông tin cá nhân của tôi còn đúng không?"
- "Có cần update preferences nào không?"
- "Working style của tôi có thay đổi gì không?"

**Commands:**
```
"Xem USER.md hiện tại"
"Cập nhật timezone của tôi thành..."
"Thêm sở thích mới: ..."
```

---

### 2. **LONG-TERM MEMORY**
**File:** `MEMORY.md`

**Câu hỏi gợi ý:**
- "Những projects nào đang active?"
- "Decision nào cần update?"
- "Lessons learned nào cần thêm?"

**Commands:**
```
"Tóm tắt MEMORY.md"
"Projects hiện tại của tôi là gì?"
"Thêm decision mới: ..."
"Cập nhật progress project X"
```

---

### 3. **WORK PROJECTS**
**File:** `memory/work-projects.md`

**Câu hỏi gợi ý:**
- "Project X progress thế nào?"
- "Tasks nào cần ưu tiên?"
- "Milestones nào đã đạt được?"

**Commands:**
```
"Update progress project OpenClaw Setup"
"Thêm task mới vào project X"
"Đánh dấu task Y hoàn thành"
```

---

### 4. **DAILY LOGS**
**File:** `memory/YYYY-MM-DD.md`

**Câu hỏi gợi ý:**
- "Hôm nay làm được gì?"
- "Activities nào cần ghi lại?"
- "Lessons learned hôm nay?"

**Commands:**
```
"Thêm activity vào daily log hôm nay"
"Tóm tắt activities tuần này"
"Export daily logs thành report"
```

---

## 🔄 REVIEW WORKFLOW

### **Step 1: Request Review**
```
"Review memory với tôi"
"Xem lại USER.md"
"Cập nhật work-projects.md"
```

### **Step 2: Agent Presents Info**
Agent sẽ:
- Đọc file liên quan
- Tóm tắt nội dung chính
- Highlight thông tin quan trọng
- Đề xuất cập nhật

### **Step 3: User Confirms/Edits**
Bạn:
- Xác nhận thông tin đúng
- Sửa thông tin sai
- Bổ sung thông tin mới
- Xóa thông tin cũ

### **Step 4: Agent Updates Files**
Agent sẽ:
- Edit files theo yêu cầu
- Commit changes (auto-approved)
- Log vào commit-history.md

---

## 📋 TEMPLATE CÂU HỎI REVIEW

### Daily Review (5 minutes)
```
1. "Hôm nay tôi làm được gì?"
2. "Có gì cần update vào daily log không?"
3. "Projects progress thế nào?"
```

### Weekly Review (15 minutes)
```
1. "Tuần này đạt được milestones gì?"
2. "Projects nào cần update status?"
3. "MEMORY.md có gì cần consolidate?"
4. "Lessons learned gì mới?"
```

### Monthly Review (30 minutes)
```
1. "Tháng này hoàn thành được gì?"
2. "Goals nào đạt/không đạt?"
3. "Cần điều chỉnh hướng đi không?"
4. "MEMORY.md cần cleanup gì?"
```

---

## 🎯 BEST PRACTICES

### ✅ NÊN LÀM
- Review daily log cuối ngày (5 min)
- Review work-projects weekly (15 min)
- Consolidate MEMORY.md monthly (30 min)
- Hỏi agent khi không nhớ thông tin
- Update ngay khi có thay đổi lớn

### ❌ TRÁNH
- Để memory quá cũ không update
- Không review trong thời gian dài
- Thay đổi mà không ghi vào files
- Dựa vào "mental notes"

---

## 💡 EXAMPLE CONVERSATIONS

### Example 1: Update Project Progress
```
You: "Update progress project OpenClaw Setup"
Agent: "Hiện tại 60%. Bạn muốn update lên bao nhiêu?"
You: "Lên 75% đi, vừa xong phần memory system"
Agent: "✅ Updated work-projects.md. Committing..."
```

### Example 2: Review USER.md
```
You: "Xem USER.md của tôi"
Agent: "Đây là thông tin hiện tại: [summary]. Có gì cần update?"
You: "Thêm sở thích mới: automation workflows"
Agent: "✅ Added to USER.md. Committing..."
```

### Example 3: Consolidate Memory
```
You: "Consolidate MEMORY.md với daily logs tuần này"
Agent: "Tôi sẽ review 7 daily logs và extract insights. OK?"
You: "OK"
Agent: "Đây là các insights: [list]. Thêm vào MEMORY.md nhé?"
You: "✅ Approve"
Agent: "✅ MEMORY.md updated. Committing..."
```

---

## 📊 QUICK REFERENCE

| File | Review Frequency | Time Needed |
|------|------------------|-------------|
| Daily logs | Daily (end of day) | 5 min |
| work-projects.md | Weekly | 15 min |
| MEMORY.md | Monthly | 30 min |
| USER.md | As needed | 5 min |
| TOOLS.md | As needed | 5 min |

---

## 🔧 HELPFUL COMMANDS

```
# Review commands
"Review memory với tôi"
"Show me USER.md"
"Tóm tắt work-projects.md"
"Daily log hôm nay có gì?"

# Update commands
"Update progress project X lên Y%"
"Thêm task mới: ..."
"Sửa thông tin: ..."
"Xóa cái này không cần nữa"

# Consolidation commands
"Consolidate weekly logs"
"Extract insights từ daily logs"
"Clean up MEMORY.md"
```

---

*Updated: 2026-03-12*
*Next Review: Daily at 10:00 AM (auto-report)*
