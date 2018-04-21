cd ..\..\..
md tmp
del tmp\*.txt
java -cp bin abc089.c.Main < data\in\abc089\c\q1.txt > tmp\q1.txt
java -cp bin abc089.c.Main < data\in\abc089\c\q2.txt > tmp\q2.txt
java -cp bin abc089.c.Main < data\in\abc089\c\q3.txt > tmp\q3.txt
fc tmp\q1.txt data\out\abc089\c\q1.txt
fc tmp\q2.txt data\out\abc089\c\q2.txt
fc tmp\q3.txt data\out\abc089\c\q3.txt
pause
