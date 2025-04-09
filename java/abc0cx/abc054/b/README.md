# Main01
n,mを読み込み、String[] aa,abに読み込む。
y,xを0からn回ループし、check(y, x)を呼び出す。
ループ終了したら、ng。

checkの中で、
y+m>=n || x+m>=nの場合、戻る。
iを0からm回ループし、a=aa[y+i]、b=ab[i]を入れる。
jを0からm回ループし、a.charAt(x+j)!=b.charAt(j)の場合、戻る。
ループ終了したら、ok。
WA1個。

# Main
Main01を元に、
y+m>=n || x+m>=nの場合、戻る。
を
y+m>n || x+m>nの場合、戻る。
に修正。

