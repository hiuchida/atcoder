@echo off
set CD=%~dp0
cd %CD%
call ..\run.bat abc096 a
cd %CD%
call ..\run.bat abc096 b
cd %CD%
call ..\run.bat abc096 c
cd %CD%
call ..\run.bat abc096 d
pause
