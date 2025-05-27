# Main
nを読み込み、int[] aryに読み込む。
int[] dpを用意し、INT\_MAXで初期化し、dp[1]=0を入れる。

iを1からnまでループし、
j=i+1を入れ、dp[j]をdp[i]+Math.abs(ary[j]-ary[i])の最小値に更新する。
j=i+2を入れ、j<=nの場合、dp[j]をdp[i]+Math.abs(ary[j]-ary[i])の最小値に更新する。

dp[n]を出力する。
