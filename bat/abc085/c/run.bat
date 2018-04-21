cd ..\..\..
md tmp
del tmp\*.txt
java -cp bin abc085.c.Main < data\in\abc085\c\q1.txt > tmp\q1.txt
java -cp bin abc085.c.Main < data\in\abc085\c\q2.txt > tmp\q2.txt
java -cp bin abc085.c.Main < data\in\abc085\c\q3.txt > tmp\q3.txt
java -cp bin abc085.c.Main < data\in\abc085\c\q4.txt > tmp\q4.txt
fc tmp\q1.txt data\out\abc085\c\q1.txt
fc tmp\q2.txt data\out\abc085\c\q2.txt
fc tmp\q3.txt data\out\abc085\c\q3.txt
fc tmp\q4.txt data\out\abc085\c\q4.txt
pause
