# Main
nを読み込み、int[] aryに読み込み、Counter cntを用意し、cnt.inc(ary[i])にカウントする。
ans=0を用意し、cnt.keySet()の要素vをループし、
c1=cnt.get(v)、c2=cnt.get(v-1)、c3=cnt.get(v+1)、c=c1+c2+c3を入れ、
ansをcの最大値に更新する。
ansを出力する。
AC 566ms
