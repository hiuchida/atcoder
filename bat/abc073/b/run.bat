cd ..\..\..
md tmp
del tmp\*.txt
java -cp bin abc073.b.Main < data\in\abc073\b\q1.txt > tmp\q1.txt
java -cp bin abc073.b.Main < data\in\abc073\b\q2.txt > tmp\q2.txt
fc tmp\q1.txt data\out\abc073\b\q1.txt
fc tmp\q2.txt data\out\abc073\b\q2.txt
pause
