@echo off
setlocal EnableExtensions

REM === Instellingen (pas aan) ===
set "JAR_URL=https://github.com/ItsNukea/suriVlloRkciR/releases/download/<tag>/<bestand>.jar"
set "JAR_NAME=<bestand>.jar"

REM === Doelmap: Downloads ===
set "DEST_DIR=%USERPROFILE%\Downloads"
set "DEST_PATH=%DEST_DIR%\%JAR_NAME%"


REM === Download (PowerShell) ===
powershell -NoProfile -ExecutionPolicy Bypass -Command ^
  "Invoke-WebRequest -Uri '%JAR_URL%' -OutFile '%DEST_PATH%' -UseBasicParsing"

if errorlevel 1 (
  echo Download mislukt.
  exit /b 1
)

REM === (Optioneel) controleren of het bestand bestaat ===
if not exist "%DEST_PATH%" (
  echo Bestand niet gevonden na download: "%DEST_PATH%"
  exit /b 1
)

REM === Run ===
REM Zorg dat Java beschikbaar is op PATH, of gebruik een volledig pad naar java.exe
java -jar "%DEST_PATH%"

endlocal