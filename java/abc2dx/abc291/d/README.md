# Main
nを読み込み、int[] aa,abに読み込む。
long[][] dpを用意し、dp[0][0]=1、dp[0][1]=1を初期化する。
iを0からi<n-1までループし、
aa[i]!=aa[i+1]の場合、dp[i+1][0]=modadd(dp[i+1][0], dp[i][0])を更新する。
aa[i]!=ab[i+1]の場合、dp[i+1][1]=modadd(dp[i+1][1], dp[i][0])を更新する。
ab[i]!=aa[i+1]の場合、dp[i+1][0]=modadd(dp[i+1][0], dp[i][1])を更新する。
ab[i]!=ab[i+1]の場合、dp[i+1][1]=modadd(dp[i+1][1], dp[i][1])を更新する。

modadd(dp[n-1][0], dp[n-1][1])を出力する。
