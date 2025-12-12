@echo off
setlocal EnableExtensions

set "JAR_URL=https://github.com/ItsNukea/suriVlloRkciR/releases/download/v0.1-alpha/suriVlloRkciR-v0.1-alpha-all-deps.jar"
set "JAR_NAME=suriVlloRkciR-v0.1-alpha-all-deps.jar"

set "DEST_DIR=%USERPROFILE%\Downloads"

powershell -NoProfile -ExecutionPolicy Bypass -Command ^
  "iwr -Uri '%JAR_URL%' -OutFile '%DEST_PATH%' -UseBasicParsing"

if errorlevel 1 (
  echo Download failed.
  REM exit /b 1
)

cd "C:\Users\keanu.tersteeg\CodeProjects\Java\suriVlloRkciR"
REM cd %DEST_DIR%
java -jar %JAR_NAME%

endlocal