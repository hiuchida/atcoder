cd ..\..\..
md tmp
del tmp\*.txt
java -cp bin abc072.b.Main < data\in\abc072\b\q1.txt > tmp\q1.txt
java -cp bin abc072.b.Main < data\in\abc072\b\q2.txt > tmp\q2.txt
java -cp bin abc072.b.Main < data\in\abc072\b\q3.txt > tmp\q3.txt
java -cp bin abc072.b.Main < data\in\abc072\b\q4.txt > tmp\q4.txt
fc tmp\q1.txt data\out\abc072\b\q1.txt
fc tmp\q2.txt data\out\abc072\b\q2.txt
fc tmp\q3.txt data\out\abc072\b\q3.txt
fc tmp\q4.txt data\out\abc072\b\q4.txt
pause
