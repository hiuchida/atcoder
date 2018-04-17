cd ..\..\..
md tmp
del tmp\*.txt
java -cp bin arc092.a.Main < data\in\arc092\a\q1.txt > tmp\q1.txt
java -cp bin arc092.a.Main < data\in\arc092\a\q2.txt > tmp\q2.txt
java -cp bin arc092.a.Main < data\in\arc092\a\q3.txt > tmp\q3.txt
java -cp bin arc092.a.Main < data\in\arc092\a\q4.txt > tmp\q4.txt
java -cp bin arc092.a.Main < data\in\arc092\a\q5.txt > tmp\q5.txt
fc tmp\q1.txt data\out\arc092\a\q1.txt
fc tmp\q2.txt data\out\arc092\a\q2.txt
fc tmp\q3.txt data\out\arc092\a\q3.txt
fc tmp\q4.txt data\out\arc092\a\q4.txt
fc tmp\q5.txt data\out\arc092\a\q5.txt
pause
