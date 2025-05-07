# Main
n,kを読み込み、int[] aa,abに読み込む。
int[] acを用意し、ab\[i]をac\[n+i\]にコピーする。
ac[i]=aa[i]-ab[i]を入れる。
acをソートする。
ans=0を用意し、ans+=ac[ac.length-1-i]を加える。
ansを出力する。
AC 778ms
