# Main
nを読み込み、int[] iaに読み込み、
int[] aryを用意し、iを0からn回ループし、ary[i+1] = ia[i]を入れる。
ans = 0を用意し、iを1からn+1までループし、ans += abs(ary[i] - ary[i-1])を入れる。
iを1からnまでループし、
ao = abs(ary[i] - ary[i-1]) + abs(ary[i+1] - ary[i])
an = abs(ary[i+1] - ary[i-1])を入れ、
ans - ao + anを出力する。
AC 250ms

# Main\_fix
書き直し。

int[] ary=new int[n+2]を用意し、iを1からnまで読み込み、
sum+=abs(ary[i]-ary[i-1])を入れる。（ary\[1\]-0と0-ary\[n\]の両方を含む）

d1=Math.abs(ary[i]-ary[i-1])
d2=Math.abs(ary[i+1]-ary[i])
d3=Math.abs(ary[i+1]-ary[i-1])を入れ、
sum-d1-d2+d3を出力する。
AC 646ms

