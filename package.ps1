<#
    Builds a self-contained Windows build of King's Call with jpackage.

    Default: a portable app-image folder  -> build\dist\KingsCall\KingsCall.exe
             (no JDK needed on the target machine; just copy the folder)

    -Installer : also build a double-click installer -> build\dist\KingsCall-1.0.0.exe
                 (requires the WiX Toolset v3 on PATH: https://wixtoolset.org/)

    Requires a JDK 17+ (for jpackage) on PATH. Optional icon: packaging\KingsCall.ico
#>
param(
    [switch]$Installer
)

$ErrorActionPreference = "Stop"
$root       = $PSScriptRoot
$version    = "1.0.0"
$jar        = Join-Path $root "resources\SaxionApp.jar"
$build      = Join-Path $root "build"
$classes    = Join-Path $build "classes"
$stage      = Join-Path $build "app-input"
$dist       = Join-Path $build "dist"
$icon       = Join-Path $root "packaging\KingsCall.ico"

Write-Host "==> Cleaning build\"
Remove-Item -Recurse -Force $build -ErrorAction SilentlyContinue
New-Item -ItemType Directory -Force -Path $classes, $stage, $dist | Out-Null

Write-Host "==> Compiling"
$sources = Get-ChildItem -Recurse -Path (Join-Path $root "BasicGame\src") -Filter *.java |
    ForEach-Object { $_.FullName }
& javac -cp $jar -d $classes $sources

Write-Host "==> Building KingsCall.jar"
$manifest = Join-Path $build "manifest.txt"
"Main-Class: kingscall.KingsCall`nClass-Path: SaxionApp.jar`n" | Set-Content -NoNewline $manifest
& jar --create --file (Join-Path $stage "KingsCall.jar") --manifest $manifest -C $classes .

Write-Host "==> Staging resources"
Copy-Item $jar (Join-Path $stage "SaxionApp.jar")
Copy-Item -Recurse (Join-Path $root "resources") (Join-Path $stage "resources")
Remove-Item (Join-Path $stage "resources\SaxionApp.jar") -ErrorAction SilentlyContinue

$common = @(
    "--name", "KingsCall",
    "--app-version", $version,
    "--vendor", "KingsCall",
    "--input", $stage,
    "--main-jar", "KingsCall.jar",
    "--main-class", "kingscall.KingsCall",
    "--java-options", "-Dkingscall.home=`$APPDIR",
    "--java-options", "-Xmx512m",
    "--dest", $dist
)
if (Test-Path $icon) { $common += @("--icon", $icon) }

Write-Host "==> jpackage (app-image)"
& jpackage --type app-image @common
Write-Host "    -> $dist\KingsCall\KingsCall.exe"

if ($Installer) {
    Write-Host "==> jpackage (installer, needs WiX)"
    & jpackage --type exe @common --win-dir-chooser --win-menu --win-shortcut
    Write-Host "    -> $dist\KingsCall-$version.exe"
}

Write-Host "`nDone."
