@echo off
setlocal EnableExtensions

set "JAR_URL=https://github.com/ItsNukea/suriVlloRkciR/releases/download/v0.1-alpha/suriVlloRkciR.jar"
set "JAR_NAME=suriVlloRkciR.jar"

set "DEST_DIR=%USERPROFILE%\Downloads"
set "DEST_PATH=%DEST_DIR%\%JAR_NAME%"

powershell -NoProfile -ExecutionPolicy Bypass -Command ^
  "iwr -Uri '%JAR_URL%' -OutFile '%DEST_PATH%' -UseBasicParsing"

if errorlevel 1 (
  echo Download failed.
  exit /b 1
)

java -jar "%DEST_PATH%"

endlocal