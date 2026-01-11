@echo off

findstr "Score = " %1 > tmp.log
for /F "tokens=3" %%i in (tmp.log) do echo %%i
rem for /F "delims== tokens=2" %%i in (tmp.log) do echo %%i
