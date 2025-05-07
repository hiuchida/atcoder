# Main
nを読み込み、int[][] aryに読み込む。
ans=0を用意し、iを0からn回ループし、sum=0を用意し、
jを0からiまでループし、sum+=ary[0][j]を加え、
jをiからn-1までループし、sum+=ary[1][j]を加え、
ansにsumの最大値を更新する。
AC 84ms

# Main\_sum
累積和で計算する。

int[][] sumを用意し、iを0から2回ループし、jを0からn回ループし、
sum[i][j+1]=sum[i][j]+ary[i][j]を入れる。
x1=sum[0][i+1]-sum[0][0]+sum[1][n]-sum[1][i]を入れ、
ansにx1の最大値を更新する。
AC 84ms

# Main\_dp
動的計画法で計算する。

int[][] dpを用意し、iを0から2回ループし、jを0からn回ループし、
j>0の場合、dp[i][j]にdp[i][j-1]+ary[i][j]の最大値を更新する。
i>0の場合、dp[i][j]にdp[i-1][j]+ary[i][j]の最大値を更新する。
dp[1][n]を出力する。
AC 85ms

