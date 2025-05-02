# Main
abc211/cと同じ。

n,sを読み込み、char[] aryに展開する。
long[][] dpを用意し、dp[0]\[j\]=1に初期化する。
jを0からt.length()回ループし、ch=t.charAt(j)に入れる。
iを0からn回ループし、
ary[i]==chの場合、dp[j+1][i+1]=modadd(dp[j+1][i], dp[j][i])を入れる。
それ以外は、dp[j+1][i+1]=dp[j+1][i]を入れる。
dp[7][n]を出力する。
