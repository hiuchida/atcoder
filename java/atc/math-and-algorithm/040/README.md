# Main01
nを読み込み、int[] aryに読み込む。
int[] sumを用意し、iを1からn-1までループし、sum[i]=sum[i-1]+ary[i]を入れる。

m,preを読み込み、ans=0を用意し、m-1回ループし、
bを読み込み、ans+=abs(sum[b-1]-sum[pre-1])、pre=bを入れる。

ansを出力する。
WA14個。

# Main
Main01を元に、aが10^7、nが10^5のため、累積和が32ビットを越える。
int[] sumをlong[] sumに修正。
AC 642ms

