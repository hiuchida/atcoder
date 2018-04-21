cd ..\..\..
md tmp
del tmp\*.txt
java -cp bin arc090.a.Main < data\in\arc090\a\q1.txt > tmp\q1.txt
java -cp bin arc090.a.Main < data\in\arc090\a\q2.txt > tmp\q2.txt
java -cp bin arc090.a.Main < data\in\arc090\a\q3.txt > tmp\q3.txt
java -cp bin arc090.a.Main < data\in\arc090\a\q4.txt > tmp\q4.txt
fc tmp\q1.txt data\out\arc090\a\q1.txt
fc tmp\q2.txt data\out\arc090\a\q2.txt
fc tmp\q3.txt data\out\arc090\a\q3.txt
fc tmp\q4.txt data\out\arc090\a\q4.txt
pause
