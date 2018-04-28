@echo off
pushd
cd %~dp0\..

if not exist tmp (
md tmp
)
del tmp\*.txt

call :check %1 %2 q1
call :check %1 %2 q2
call :check %1 %2 q3
call :check %1 %2 q4
call :check %1 %2 q5
call :check %1 %2 q6
call :check %1 %2 q7
call :check %1 %2 q8
call :check %1 %2 q9
call :check %1 %2 q11
call :check %1 %2 q12
call :check %1 %2 q13
call :check %1 %2 q14
call :check %1 %2 q15
call :check %1 %2 q16
call :check %1 %2 q17
call :check %1 %2 q18
call :check %1 %2 q19

popd
exit /b 0

:check
if exist data\in\%1\%2\%3.txt (
java -cp bin %1.%2.Main < data\in\%1\%2\%3.txt > tmp\%3.txt
fc tmp\%3.txt data\out\%1\%2\%3.txt
)
exit /b 0
