# Main01
n,a,bを読み込み、int[] aryに読み込む。
ans=0を用意し、iを1からn-1までループし、
d=ary[i]-ary[i-1]を入れ、ans+=Math.min(a*d, b)を更新する。
ansを出力する。
WA9個。

# Main
Main01を元に、
int d=ary[i]-ary[i-1];
を
long d=ary[i]-ary[i-1];
に修正する。

