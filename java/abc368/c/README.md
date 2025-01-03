# Main
最初、t=0としたので、t%3==0となってしまい、最初の1撃だけ特殊な判定をしていたら、
妙に間違っていて、t=1にしたらすんなりいった。
			while (h>=5) {
				h -= 5;
				ans += 3;
				t += 3;
			}
は気が付いたが、最初の辻褄が合っていないことが尾を引いて、
int num=h/5;のような計算を避けて、100倍づつ増やして、ループ回数を減らした。
N=10^5、H=10^9なので、まともに計算したらTLEなんだろう。
			while (h>=5000000) {
				h -= 5000000;
				ans += 3000000;
				t += 3000000;
			}
			while (h>=50000) {
				h -= 50000;
				ans += 30000;
				t += 30000;
			}
			while (h>=500) {
				h -= 500;
				ans += 300;
				t += 300;
			}
			while (h>=5) {
				h -= 5;
				ans += 3;
				t += 3;
			}

long ans = 0;
long t = 1;
int tt=(int)(t%3);
となっているのが、ttは0から2までサイクルしていればよく、tは不要。
ans++とt++が同じ位置なので、初期値が異なるが、t%3の直前にt++すればよい。


# Main_fix
見直して、デバッグ出力を分かりやすくした。
毎回、System.out.printlnをコメントアウトして提出するのが面倒なので、staticメソッドにした。

0: 0 1 1 entry
0: 0 1 1 skip
0: 1 2 0 once
1: 1 2 12 entry
1: 7 2 2 skip
1: 8 0 1 once
1: 9 1 -2 once
2: 9 1 123 entry
2: 81 1 3 skip
2: 82 2 2 once
2: 83 0 1 once
2: 84 1 -2 once
3: 84 1 1234 entry
3: 822 1 4 skip
3: 823 2 3 once
3: 824 0 2 once
3: 825 1 -1 once
4: 825 1 12345 entry
4: 8232 1 0 skip
5: 8232 1 123456 entry
5: 82305 1 1 skip
5: 82306 2 0 once
6: 82306 2 1234567 entry
6: 823045 2 2 skip
6: 823046 0 1 once
6: 823047 1 -2 once
7: 823047 1 12345678 entry
7: 8230452 1 3 skip
7: 8230453 2 2 once
7: 8230454 0 1 once
7: 8230455 1 -2 once
8: 8230455 1 123456789 entry
8: 82304526 1 4 skip
8: 82304527 2 3 once
8: 82304528 0 2 once
8: 82304529 1 -1 once
82304529