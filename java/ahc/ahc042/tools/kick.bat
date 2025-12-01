@echo off

echo execute %1
if not exist out\. md out
rem py Main00.py <in\%1.txt >out\%1.txt
java Main <in\%1.txt >out\%1.txt
vis in\%1.txt out\%1.txt
echo.

exit /b
