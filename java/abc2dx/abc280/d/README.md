# Main01
kを読み込む。
long[] dpを用意し、dp[1]=1を初期化する。
iを2からdp.length;回ループし、dp[i]=dp[i-1]*iを更新する。
dp[i]<0の場合、中断する。（i=21でオーバーフローする）

iを2からdp.length;回ループし、x=dp[i]を入れ、
x%k==0の場合、iを出力し、終了する。
WA57個。

# Main

