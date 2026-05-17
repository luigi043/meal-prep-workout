param()

$ErrorActionPreference = "Stop"
$scriptRoot = if ($PSScriptRoot) { $PSScriptRoot } else { Split-Path -Parent $MyInvocation.MyCommand.Path }
$cwd = (Get-Location).Path
$repoRoot = if (Test-Path (Join-Path $cwd "settings.gradle.kts")) {
    $cwd
} else {
    Split-Path -Parent $scriptRoot
}
$studioJdk = "C:\Program Files\Android\Android Studio\jbr\bin\java.exe"
$localProperties = Join-Path $repoRoot "local.properties"
$gradleWrapper = Join-Path $repoRoot "gradlew.bat"

Write-Host "Plano 3 Meses - environment check"
Write-Host "Repository: $repoRoot"
Write-Host ""

if (Test-Path $studioJdk) {
    Write-Host "[OK] Android Studio bundled JDK found:"
    Write-Host "     $studioJdk"
} else {
    Write-Host "[WARN] Android Studio bundled JDK not found at:"
    Write-Host "       $studioJdk"
    Write-Host "       Open Android Studio and install/update it, or set JAVA_HOME manually."
}

if (Test-Path $localProperties) {
    $sdkLine = Get-Content $localProperties | Where-Object { $_ -match "^sdk\.dir=" } | Select-Object -First 1
    if ($sdkLine) {
        $sdkPath = $sdkLine.Substring("sdk.dir=".Length).Replace("\:", ":").Replace("\\", "\")
        Write-Host "[OK] Android SDK path from local.properties:"
        Write-Host "     $sdkPath"
    } else {
        Write-Host "[WARN] local.properties exists, but sdk.dir is missing."
    }
} else {
    Write-Host "[WARN] local.properties not found."
    Write-Host "       Android Studio usually creates it after opening the project."
}

if (Test-Path $gradleWrapper) {
    Write-Host "[OK] Gradle wrapper found:"
    Write-Host "     $gradleWrapper"
} else {
    Write-Host "[WARN] gradlew.bat not found."
    Write-Host "       Easiest next step:"
    Write-Host "       1. Open the folder in Android Studio."
    Write-Host "       2. Let Gradle sync."
    Write-Host "       3. Use Android Studio Run or Build APK."
    Write-Host "       4. Add a Gradle wrapper later if you want command-line builds."
}

Write-Host ""
Write-Host "Done."
