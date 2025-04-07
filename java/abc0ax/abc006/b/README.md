# Main
nを読み込む。
int[] dpを用意し、dp[3]=1を初期化し、iを4からnまでループし、
dp[i]=dp[i-3]+dp[i-2]+dp[i-1]、dp[i]%=Mを計算する。
dp[n]を出力する。

calcを再帰呼び出ししたら、10^5でStackOverflowErrorとなった。
