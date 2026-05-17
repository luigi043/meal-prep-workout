param()

$ErrorActionPreference = "Stop"
$scriptRoot = if ($PSScriptRoot) { $PSScriptRoot } else { Split-Path -Parent $MyInvocation.MyCommand.Path }
$cwd = (Get-Location).Path
$repoRoot = if (Test-Path (Join-Path $cwd "settings.gradle.kts")) {
    $cwd
} else {
    Split-Path -Parent $scriptRoot
}
$gradleWrapper = Join-Path $repoRoot "gradlew.bat"
$studioJbr = "C:\Program Files\Android\Android Studio\jbr"

Write-Host "Plano 3 Meses - debug build"
Write-Host "Repository: $repoRoot"
Write-Host ""

if (-not (Test-Path $gradleWrapper)) {
    Write-Host "[STOP] gradlew.bat was not found."
    Write-Host ""
    Write-Host "Build from Android Studio instead:"
    Write-Host "1. Open Android Studio."
    Write-Host "2. File > Open, then select this project folder."
    Write-Host "3. Let Gradle sync finish."
    Write-Host "4. Run the app on an emulator or phone."
    Write-Host "5. For an APK: Build > Build Bundle(s) / APK(s) > Build APK(s)."
    exit 1
}

if (Test-Path $studioJbr) {
    $env:JAVA_HOME = $studioJbr
    $env:Path = "$studioJbr\bin;$env:Path"
    Write-Host "[OK] Using Android Studio bundled JDK:"
    Write-Host "     $studioJbr"
} else {
    Write-Host "[WARN] Android Studio bundled JDK was not found."
    Write-Host "       Continuing with the current shell Java configuration."
}

Push-Location $repoRoot
try {
    & $gradleWrapper assembleDebug --console=plain
} finally {
    Pop-Location
}
