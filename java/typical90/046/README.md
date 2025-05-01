# Main
nを読み込み、Counter[] cntを用意する。
jを0から2までループし、cnt[j]=new Counter()を用意する。
iを0からn回ループし、aを読み込み、a%=Mを計算し、cnt[j].inc(a)にカウントする。

ans=0を用意し、cnt[0].keySet()、cnt[1].keySet()、cnt[2].keySet()の要素a,b,cをループし、
abc=a+b+cを入れ、
abc%M==0の場合、x=cnt[0].get(a)、x*=cnt[1].get(b)、x*=cnt[2].get(c)を計算し、ans+=xに加える。
ansを出力する。
