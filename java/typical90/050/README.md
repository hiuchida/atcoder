# Main
n,lを読み込み、long[] dpを用意し、dp[0]=1を初期化する。
iを0からn-1までループし、
i+1<=nの場合、dp[i+1]=modadd(dp[i+1], dp[i])を加える。
i+l<=nの場合、dp[i+l]=modadd(dp[i+l], dp[i])を加える。
dp[n]を出力する。
AC 74ms
