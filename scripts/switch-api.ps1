#!/usr/bin/env pwsh
# switch-api.ps1 - Switch between OpenRouter and Alibaba DashScope
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8

param(
    [Parameter(Mandatory=$true)]
    [ValidateSet("openrouter", "alibaba", "dashscope")]
    [string]$to
)

$CONFIG_PATH = "$env:USERPROFILE\.openclaw\openclaw.json"

# Đọc config
$config = Get-Content $CONFIG_PATH -Raw | ConvertFrom-Json

# Model mappings
$models = @{
    "openrouter" = "openrouter/qwen-2.5-72b-instruct"
    "alibaba" = "custom-dashscope-intl-aliyuncs-com/qwen3.5-plus"
    "dashscope" = "custom-dashscope-intl-aliyuncs-com/qwen3.5-plus"
}

# Đổi default model
$config.agents.defaults.model.primary = $models[$to]

# Lưu config
$config | ConvertTo-Json -Depth 10 | Set-Content $CONFIG_PATH -Encoding UTF8

Write-Host "✅ Đã chuyển sang: $to" -ForegroundColor Green
Write-Host "   Model: $($models[$to])" -ForegroundColor Cyan
Write-Host "   Restart gateway để áp dụng: openclaw gateway restart" -ForegroundColor Yellow
