# ============================================
# OPENCLAW GITHUB PAGES DEPLOYMENT SCRIPT
# ============================================
# Run this in PowerShell as Administrator
# ============================================

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  OpenClaw GitHub Pages Deployment" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Configuration
$workspacePath = "C:\Users\mayao2\.openclaw\workspace"
$githubUsername = "ngocecom"  # Change if your username is different
$repoName = "openclaw"
$repoUrl = "https://github.com/$githubUsername/$repoName.git"

# Navigate to workspace
Write-Host "Step 1/6: Navigating to workspace..." -ForegroundColor Yellow
Set-Location $workspacePath
Write-Host "✅ At: $workspacePath" -ForegroundColor Green
Write-Host ""

# Check if git is installed
Write-Host "Step 2/6: Checking Git installation..." -ForegroundColor Yellow
if (!(Get-Command git -ErrorAction SilentlyContinue)) {
    Write-Host "❌ Git is not installed!" -ForegroundColor Red
    Write-Host ""
    Write-Host "Please install Git first:" -ForegroundColor Yellow
    Write-Host "   winget install Git.Git" -ForegroundColor White
    Write-Host ""
    Write-Host "Then run this script again." -ForegroundColor Yellow
    exit 1
}
Write-Host "✅ Git found: $(git --version)" -ForegroundColor Green
Write-Host ""

# Initialize git repository if not already
Write-Host "Step 3/6: Initializing repository..." -ForegroundColor Yellow
if (!(Test-Path ".git")) {
    git init
    Write-Host "✅ Repository initialized" -ForegroundColor Green
} else {
    Write-Host "✅ Repository already exists" -ForegroundColor Green
}

# Add remote origin
Write-Host ""
Write-Host "Step 4/6: Adding GitHub remote..." -ForegroundColor Yellow
git remote remove origin 2>$null
git remote add origin $repoUrl
Write-Host "✅ Remote added: $repoUrl" -ForegroundColor Green
Write-Host ""

# Rename branch to main
Write-Host "Step 5/6: Setting up main branch..." -ForegroundColor Yellow
git branch -M main
Write-Host "✅ Branch set to 'main'" -ForegroundColor Green
Write-Host ""

# Stage all files
Write-Host "Step 6/6: Staging files..." -ForegroundColor Yellow
git add -A
$filesStaged = $(git status --short).Count
Write-Host "✅ Staged $filesStaged files" -ForegroundColor Green
Write-Host ""

# Commit
Write-Host "💾 Committing changes..." -ForegroundColor Yellow
git commit -m "Deploy Mission Control Dashboard to GitHub Pages"
Write-Host "✅ Committed successfully" -ForegroundColor Green
Write-Host ""

# Display instructions for token
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  AUTHENTICATION REQUIRED" -ForegroundColor Yellow
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "GitHub requires a Personal Access Token (PAT) for authentication." -ForegroundColor White
Write-Host ""
Write-Host "📋 To create your token:" -ForegroundColor Yellow
Write-Host "   1. Open: https://github.com/settings/tokens" -ForegroundColor White
Write-Host "   2. Click 'Generate new token (classic)'" -ForegroundColor White
Write-Host "   3. Note: 'OpenClaw Dashboard'" -ForegroundColor White
Write-Host "   4. Expiration: 'No expiration'" -ForegroundColor White
Write-Host "   5. Check scope: ✅ repo" -ForegroundColor White
Write-Host "   6. Click 'Generate token'" -ForegroundColor White
Write-Host "   7. COPY the token (starts with ghp_...)" -ForegroundColor White
Write-Host ""
Write-Host "⚠️ IMPORTANT: Save your token somewhere safe!" -ForegroundColor Red
Write-Host "   You won't be able to see it again!" -ForegroundColor Red
Write-Host ""

# Get Personal Access Token
Write-Host "🔑 Enter your GitHub Personal Access Token:" -ForegroundColor Yellow
$token = Read-Host "   Paste token here (will be hidden)" -AsSecureString

# Convert secure string to plain text for git
$BSTR = [System.Runtime.InteropServices.Marshal]::SecureStringToBSTR($token)
$PlainToken = [System.Runtime.InteropServices.Marshal]::PtrToStringAuto($BSTR)

if ([string]::IsNullOrWhiteSpace($PlainToken)) {
    Write-Host ""
    Write-Host "❌ No token provided. Deployment cancelled." -ForegroundColor Red
    Write-Host ""
    Write-Host "To retry, run this script again." -ForegroundColor Yellow
    exit 1
}

Write-Host ""
Write-Host "🚀 Pushing to GitHub..." -ForegroundColor Yellow
Write-Host "   (This may take 1-2 minutes depending on file size)" -ForegroundColor Gray
Write-Host ""

# Push to GitHub using token
$env:GIT_ASKPASS = "echo"
$pushResult = git -c credential.helper=store push -u origin main 2>&1

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host "  ✅ DEPLOYMENT SUCCESSFUL!" -ForegroundColor Green
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "🎉 Your code is now on GitHub!" -ForegroundColor Green
    Write-Host ""
    Write-Host "📊 NEXT STEPS:" -ForegroundColor Yellow
    Write-Host ""
    Write-Host "1️⃣  Enable GitHub Pages:" -ForegroundColor White
    Write-Host "    Open: https://github.com/$githubUsername/$repoName/settings/pages" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "2️⃣  Configure Pages:" -ForegroundColor White
    Write-Host "    - Source: Deploy from branch" -ForegroundColor Gray
    Write-Host "    - Branch: main" -ForegroundColor Gray
    Write-Host "    - Folder: /01-VALUE-CREATION/multi-agent-system/mission-control/" -ForegroundColor Gray
    Write-Host "    - Click SAVE" -ForegroundColor Gray
    Write-Host ""
    Write-Host "3️⃣  Wait 1-2 minutes for deployment..." -ForegroundColor White
    Write-Host ""
    Write-Host "4️⃣  Access your dashboard:" -ForegroundColor White
    Write-Host "    https://$githubUsername.github.io/$repoName/01-VALUE-CREATION/multi-agent-system/mission-control/" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host "  🎮 Mission Control Dashboard Live!" -ForegroundColor Green
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host ""
} else {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Red
    Write-Host "  ❌ PUSH FAILED" -ForegroundColor Red
    Write-Host "========================================" -ForegroundColor Red
    Write-Host ""
    Write-Host "Error details:" -ForegroundColor Yellow
    Write-Host "$pushResult" -ForegroundColor Gray
    Write-Host ""
    Write-Host "🔧 Common fixes:" -ForegroundColor Yellow
    Write-Host ""
    Write-Host "1. Check token is valid (starts with ghp_...)" -ForegroundColor White
    Write-Host "2. Make sure token has 'repo' scope" -ForegroundColor White
    Write-Host "3. Verify repository exists: https://github.com/$githubUsername/$repoName" -ForegroundColor White
    Write-Host "4. Repository must be Public for GitHub Pages" -ForegroundColor White
    Write-Host ""
    Write-Host "💡 To retry:" -ForegroundColor Yellow
    Write-Host "   Run this script again with a new token" -ForegroundColor White
    Write-Host ""
}
