param()

$ErrorActionPreference = "Stop"
$scriptRoot = if ($PSScriptRoot) { $PSScriptRoot } else { Split-Path -Parent $MyInvocation.MyCommand.Path }
$cwd = (Get-Location).Path
$repoRoot = if (Test-Path (Join-Path $cwd "settings.gradle.kts")) {
    $cwd
} else {
    Split-Path -Parent $scriptRoot
}

Write-Host "Plano 3 Meses - git status"
Write-Host "Repository: $repoRoot"
Write-Host ""

Push-Location $repoRoot
try {
    $gitOptions = @("-c", "safe.directory=$repoRoot", "-c", "core.excludesFile=")
    git @gitOptions branch --show-current
    git @gitOptions log -1 --oneline
    Write-Host ""
    git @gitOptions status --short
} finally {
    Pop-Location
}
