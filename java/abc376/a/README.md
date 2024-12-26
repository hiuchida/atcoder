# Main0
WA。
初期値を適当に-999としたが、C<=1000のため足りない。
おそらくfor (int i = 0; i < n; i++)のsc.nextInt();にこだわり過ぎて、
初期値を
int tt = sc.nextInt();
int ans = 1;
にした方が自然。
もしくは最初にint[] aryにsc.nextInt();で読み込んで、ary[0]を初期値にする。

# Main
初期値を-9999に修正。

# Main_nextInt
初期値をint tt = sc.nextInt();とした。

		int tt = -9999;
		int ans = 0;
		for (int i = 0; i < n; i++) {

		int tt = sc.nextInt();
		int ans = 1;
		for (int i = 1; i < n; i++) {
