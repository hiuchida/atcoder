# Main
sを読み込み、char[] aryに展開する。
long[][] dpを用意し、iを0からnまでループし、dp[0][i]=1を初期化する。
jを0からt.length回ループし、ch=t.charAt(j)を用意する。
iを0からnまでループし、
s.charAt(i)==chの場合、dp[j+1][i+1]=modadd(dp[j+1][i], dp[j][i])を入れる。
それ以外はdp[j+1][i+1]=dp[j+1][i]を入れる。

dp[8][n]を出力する。
