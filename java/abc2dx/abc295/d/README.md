# Main
sを読み込み、char[] aryに展開する。
x=0、Counter cntを用意し、cnt.inc(x)にカウントする。
iを0からn回ループし、v=ary[i]-'0'、b=1 << vを入れ、x ^= bを更新する。
cnt.inc(x)にカウントする。

ans=0を用意し、cnt.keySet()の要素keyでループし、
v=cnt.get(key)に入れ、ans+=(long)v*(v-1)/2を加える。（nC2=n(n-1)/2）

ansを出力する。
