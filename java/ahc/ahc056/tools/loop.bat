@echo off

javac Main.java
goto :main

:invoke
call kick.bat %1%2
exit /b

:invoke10
call :invoke %1 0
call :invoke %1 1
call :invoke %1 2
call :invoke %1 3
call :invoke %1 4
call :invoke %1 5
call :invoke %1 6
call :invoke %1 7
call :invoke %1 8
call :invoke %1 9
exit /b

:main
call :invoke10 000
call :invoke10 001
call :invoke10 002
call :invoke10 003
call :invoke10 004
call :invoke10 005
call :invoke10 006
call :invoke10 007
call :invoke10 008
call :invoke10 009
exit /b

