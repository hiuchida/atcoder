# Main
nを読み込み、int[] aryに読み込む。
int[] dpを用意し、n回ループし、
a=ary[i]を入れ、dp[2*i]=dp[a]+1、dp[2*i+1]=dp[a]+1を入れる。
2\*n+1回ループし、dp[i]を出力する。
