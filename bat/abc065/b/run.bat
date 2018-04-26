cd ..\..\..
md tmp
del tmp\*.txt
java -cp bin abc065.b.Main < data\in\abc065\b\q1.txt > tmp\q1.txt
java -cp bin abc065.b.Main < data\in\abc065\b\q2.txt > tmp\q2.txt
java -cp bin abc065.b.Main < data\in\abc065\b\q3.txt > tmp\q3.txt
fc tmp\q1.txt data\out\abc065\b\q1.txt
fc tmp\q2.txt data\out\abc065\b\q2.txt
fc tmp\q3.txt data\out\abc065\b\q3.txt
pause
