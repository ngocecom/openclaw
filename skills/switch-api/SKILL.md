# Skill: Đổi API Provider

**Command:** `đổi api` hoặc `switch api`

**Mục đích:** Chuyển đổi nhanh giữa OpenRouter và Alibaba DashScope

---

## Cách dùng

### Telegram/Direct Chat:
```
đổi api openrouter
```
hoặc
```
đổi api alibaba
```

### PowerShell:
```powershell
.\scripts\switch-api.ps1 -to openrouter
.\scripts\switch-api.ps1 -to alibaba
```

---

## Các provider có sẵn

| Provider | Model | Context | Ghi chú |
|----------|-------|---------|---------|
| `openrouter` | Qwen 2.5 72B | 128k | Đa dạng model (Claude, GPT, etc.) |
| `alibaba` | Qwen 3.5 Plus | 200k | Nhanh, ổn định, free tier |

---

## Sau khi đổi

1. **Restart gateway** (bắt buộc):
   ```
   openclaw gateway restart
   ```

2. **Kiểm tra**:
   ```
   openclaw config get agents.defaults.model.primary
   ```

---

## Lưu ý

- ⚠️ Phải restart gateway để áp dụng thay đổi
- ✅ Config được lưu tự động
- 🔄 Có thể chuyển đổi unlimited lần
- 💰 OpenRouter tính phí theo token, Alibaba có free tier

---

**Script location:** `workspace/scripts/switch-api.ps1`
