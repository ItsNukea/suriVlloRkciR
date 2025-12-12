@echo off
setlocal EnableExtensions

set "JAR_NAME=suriVlloRkciR-v0.1-alpha-all-deps.jar"
set "JAR_URL=https://github.com/ItsNukea/suriVlloRkciR/raw/main/%JAR_NAME%"
set "DEST_DIR=%USERPROFILE%\Downloads"

powershell -NoProfile -ExecutionPolicy Bypass -Command ^
  "iwr -Uri '%JAR_URL%' -OutFile '%DEST_PATH%' -UseBasicParsing"

if errorlevel 1 (
  echo Download failed.
  pause
  exit /b 1
)

cd %DEST_DIR%
java -jar %JAR_NAME%
pause

endlocal