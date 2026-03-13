# 🚀 GITHUB PAGES DEPLOYMENT SCRIPT

**Created:** March 13, 2026  
**Purpose:** Auto-deploy Mission Control Dashboard to GitHub Pages

---

## ⚠️ SECURITY NOTICE

GitHub requires **Personal Access Token (PAT)** instead of password for git operations.

**DO NOT use your regular GitHub password!**

---

## 📋 PREREQUISITES

1. **GitHub Account:** https://github.com/signup
2. **Git Installed:** `git --version`
3. **Repository Created:** https://github.com/new (name: `openclaw`)

---

## 🔑 STEP 1: CREATE PERSONAL ACCESS TOKEN

### 1.1 Go to Token Settings
Open: https://github.com/settings/tokens

### 1.2 Generate New Token
- Click **"Generate new token (classic)"**
- **Note:** `OpenClaw Dashboard Deployment`
- **Expiration:** `No expiration` (or choose 90 days)
- **Scopes:** Check ✅ **`repo`** (Full control of private repositories)
- Click **"Generate token"**

### 1.3 Copy Token
- **IMPORTANT:** Copy the token immediately (starts with `ghp_...`)
- **Save it somewhere safe** - you can't see it again!
- Example: `ghp_xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx`

---

## 🚀 STEP 2: RUN DEPLOYMENT SCRIPT

### 2.1 Open PowerShell as Administrator

### 2.2 Run This Script

```powershell
# ============================================
# OPENCLAW GITHUB PAGES DEPLOYMENT SCRIPT
# ============================================

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  OpenClaw GitHub Pages Deployment" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Configuration
$workspacePath = "C:\Users\mayao2\.openclaw\workspace"
$githubUsername = "ngocecom"  # Change if different
$repoName = "openclaw"
$repoUrl = "https://github.com/$githubUsername/$repoName.git"

# Navigate to workspace
Write-Host "📁 Navigating to workspace..." -ForegroundColor Yellow
Set-Location $workspacePath

# Check if git is installed
if (!(Get-Command git -ErrorAction SilentlyContinue)) {
    Write-Host "❌ Git is not installed. Please install Git first:" -ForegroundColor Red
    Write-Host "   winget install Git.Git" -ForegroundColor Yellow
    exit 1
}

Write-Host "✅ Git found: $(git --version)" -ForegroundColor Green
Write-Host ""

# Initialize git repository if not already
if (!(Test-Path ".git")) {
    Write-Host "📦 Initializing git repository..." -ForegroundColor Yellow
    git init
} else {
    Write-Host "✅ Git repository already initialized" -ForegroundColor Green
}

# Add remote origin
Write-Host "🔗 Adding GitHub remote..." -ForegroundColor Yellow
git remote remove origin 2>$null
git remote add origin $repoUrl
Write-Host "✅ Remote added: $repoUrl" -ForegroundColor Green
Write-Host ""

# Rename branch to main
Write-Host "🌿 Renaming branch to main..." -ForegroundColor Yellow
git branch -M main

# Stage all files
Write-Host "📦 Staging all files..." -ForegroundColor Yellow
git add -A
$filesStaged = $(git status --short).Count
Write-Host "✅ Staged $filesStaged files" -ForegroundColor Green
Write-Host ""

# Commit
Write-Host "💾 Committing changes..." -ForegroundColor Yellow
git commit -m "Deploy Mission Control Dashboard to GitHub Pages"
Write-Host "✅ Committed" -ForegroundColor Green
Write-Host ""

# Get Personal Access Token
Write-Host "🔑 Enter your GitHub Personal Access Token:" -ForegroundColor Yellow
Write-Host "   (Create at: https://github.com/settings/tokens)" -ForegroundColor Gray
Write-Host "   Token should start with 'ghp_...'" -ForegroundColor Gray
$token = Read-Host "   Paste token here" -AsSecureString

# Convert secure string to plain text
$BSTR = [System.Runtime.InteropServices.Marshal]::SecureStringToBSTR($token)
$PlainToken = [System.Runtime.InteropServices.Marshal]::PtrToStringAuto($BSTR)

Write-Host ""
Write-Host "🚀 Pushing to GitHub..." -ForegroundColor Yellow
Write-Host "   (This may take 1-2 minutes)" -ForegroundColor Gray
Write-Host ""

# Push to GitHub
$env:GIT_ASKPASS = "echo"
git -c credential.helper=store push -u origin main

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  DEPLOYMENT COMPLETE!" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "📊 Next Steps:" -ForegroundColor Yellow
Write-Host ""
Write-Host "1. Go to: https://github.com/$githubUsername/$repoName/settings/pages" -ForegroundColor White
Write-Host "2. Under 'Build and deployment':" -ForegroundColor White
Write-Host "   - Source: Deploy from branch" -ForegroundColor Gray
Write-Host "   - Branch: main" -ForegroundColor Gray
Write-Host "   - Folder: /01-VALUE-CREATION/multi-agent-system/mission-control/" -ForegroundColor Gray
Write-Host "3. Click Save" -ForegroundColor White
Write-Host ""
Write-Host "4. Wait 1-2 minutes, then access:" -ForegroundColor Yellow
Write-Host "   https://$githubUsername.github.io/$repoName/01-VALUE-CREATION/multi-agent-system/mission-control/" -ForegroundColor Cyan
Write-Host ""
Write-Host "✅ Dashboard will be live!" -ForegroundColor Green
Write-Host ""

```

---

## 📝 STEP 3: ENABLE GITHUB PAGES

After script completes:

1. **Open Settings:**
   ```
   https://github.com/ngocecom/openclaw/settings/pages
   ```

2. **Configure:**
   - **Source:** Deploy from branch
   - **Branch:** `main`
   - **Folder:** `/01-VALUE-CREATION/multi-agent-system/mission-control/`
   - Click **Save**

3. **Wait 1-2 minutes** for deployment

4. **Access Dashboard:**
   ```
   https://ngocecom.github.io/openclaw/01-VALUE-CREATION/multi-agent-system/mission-control/
   ```

---

## 🔧 TROUBLESHOOTING

### Error: "Permission denied"
- Check token has `repo` scope
- Token should start with `ghp_`
- Try regenerating token

### Error: "remote origin already exists"
```powershell
git remote remove origin
# Run script again
```

### Error: "Authentication failed"
- Token may be expired
- Regenerate token at: https://github.com/settings/tokens
- Make sure to copy entire token

### Error: "GitHub Pages not enabled"
- Repository must be **Public**
- Wait 2-3 minutes after enabling
- Check Settings → Pages for status

---

## 🎯 QUICK START

**Copy and paste this into PowerShell:**

```powershell
# Quick Deploy (if you already have token)
cd C:\Users\mayao2\.openclaw\workspace
git remote add origin https://github.com/ngocecom/openclaw.git 2>$null
git branch -M main
git add -A
git commit -m "Deploy to GitHub Pages"
git push -u origin main
```

Then enter your token when prompted!

---

## 📊 POST-DEPLOYMENT

After deployment, your dashboard will have:

- ✅ **URL:** `https://ngocecom.github.io/openclaw/...`
- ✅ **HTTPS:** Secure connection
- ✅ **Auto-update:** Push new code → auto-deploy
- ✅ **Free:** No hosting costs
- ✅ **Custom Domain:** Can add later

---

## 🔄 UPDATING DASHBOARD

To update after changes:

```powershell
cd C:\Users\mayao2\.openclaw\workspace
git add -A
git commit -m "Update dashboard"
git push
```

Changes will auto-deploy in 1-2 minutes!

---

*Created: March 13, 2026*  
*Version: 1.0.0*
