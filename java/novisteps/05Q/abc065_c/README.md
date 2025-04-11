# Main
n,mを読み込む。
abs(n-m)>1の場合、0を出力し、終了する。
init(Math.max(n, m))を呼び出す。
x1=dp[n]、x2=dp[m]、ans=modmul(x1, x2)を入れ、
n==mの場合、ans=modmul(ans, 2)を更新する。
ansを出力する。

initの中で、
long[] dpを用意し、dp[1]=1を初期化し、
iを2からnまでループし、dp[i]=modmul(dp[i-1], i)を入れる。
