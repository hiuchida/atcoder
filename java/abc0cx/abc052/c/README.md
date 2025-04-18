# Main
nを読み込み、pr=new Prime(n+1)を初期化する。
int[] expを用意し、iを2からnまでループし、
f1=factors(i)を呼び出し、f1の要素vでループし、exp[v]++にカウントする。

ans=1を用意し、iを2からnまでループし、ans=modmul(ans, exp[i]+1)を更新する。
ansを出力する。

# Main\_factorize
Mainを元に、f1=factors(i)　の代わりに　f2=factorize(i)　を呼び出す。
jを0からf2.length回ループし、exp[f2[j][0]]+=f2[j][1]に加える。

