# Main
n,mを読み込み、int[] aryに読み込む。
check(ary, n, m)の場合、0を出力し、終了する。
int ansを用意し、ansを1からn-1までループし、
check(ary, n-ans, m)の場合、ansを出力し、終了する。
ansを出力する。

checkの中で、
int[] cntを用意し、iを0からn回ループし、
a=ary[i]を入れ、a<=mの場合、cnt[a]++を加える。
iを1からmまでループし、cnt[i]==0の場合trueを返す。
falseを返す。
