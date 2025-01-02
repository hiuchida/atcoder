# Main0
解説の通りに思いついて、c個連続する個数をcをkeyにしてMap<Integer,Integer>に登録したが、
n=1の時に2を出力してしまいWA。

# Main
1つだけWAなので、最後にn==1の判定を追加してOK。

		if (n==1)
			System.out.println(1);
		else
			System.out.println(ans);

ソースを見ていて、n==2の場合も問題があったが、ジャッジデータが網羅していないため正解となっていた。

これは3を出力
2
1 4

これは2を出力
2
2 4

n==2の場合、常に3であるべき。

ここの部分、かなり無理をして、末尾の抜けているところを足しているが、
n==2の場合、dif[1]=ary[1]-ary[0]=A1で1項目の値と、1項目と2項目の差が同じ時に1件抜ける。

		for (int i=2; i<n; i++) {
			if (dif[i]==dif[i+1]) {
			:
		}
		if (dif[n-1]!=dif[n]) inc(1);

# Main_fix
int[] dif = new int[n+2];に増やし、dif[n+1] = Integer.MAX_VALUE;の番兵を置く。

無理なif (dif[n-1]!=dif[n]) inc(1);を無くしたので、mapが空となり、if (n==1)の判定は不要となった。

# Main_counter
Map<Integer, Integer>を使って、keyに対するカウントをするCounterクラスを作成し、利用する。
