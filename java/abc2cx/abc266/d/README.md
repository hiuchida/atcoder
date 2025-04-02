# Main
nを読み込み、int[] at,ax,aaに読み込む。
maxt=at[n-1]、maxx=5、idx=0を用意する。
long[][] dpを用意し、すべて-1で初期化し、dp[0][0]=0を入れる。
tを0からmaxt-1までループし、
curx=-1、cura=-1を用意し、at[idx]==t+1の場合にcurx=ax[idx]、cura=aa[idx]を入れ、idx++加える。
xを0からmaxx-1までループし、v=dp[t][x]を用意する。
v>=0の場合、最大3ヵ所へ振り分ける。
x>0の場合、curx==x-1ならばv1+=curaし、dp[t+1][x-1]へmax(dp[t+1][x-1], v1)を入れる。
x<maxx-1の場合、curx==x+1ならばv2+=curaし、dp[t+1][x+1]へmax(dp[t+1][x+1], v2)を入れる。
常に、curx==xならばv3+=curaし、dp[t+1][x]へmax(dp[t+1][x], v3)を入れる。
2重ループが終了したら、ans=0を用意し、
xを0からmaxx-1までループし、dp[maxt][x]の最大値をansを更新する。
ansを出力する。
