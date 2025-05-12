# Main01
nを読み込み、int[] ac,aaに読み込む。
int[][] dpを用意し、Nで初期化し、dp[0][0]=0を入れる。
iを1からn-1までループし、jを0からnまでループし、dp[i][j]=dp[i-1][j]を入れる。
min=Nを用意し、jを0からac[i]までループし、
i-j>=0の場合、minにdp[i-1][i-j]の最小値を更新する。
dp[i][i]にmin+1の最小値を更新する。

TreeSet<Integer> set、ans=0を用意し、iをn-1から1まで-1ループし、
a=aa[i]を用意し、a==0の場合スキップする。
set.contains(i)の場合スキップする。
set.add(i)を追加する。
ans+=dp[n-1][i]を入れる。
a>0の間ループし、set.contains(i)の場合中断する。
set.add(i)を追加する。
a--を減らし、jをac[i]から0まで-1ループし、
i-j>=0 && dp[n-1][i-j]==aの場合、a=i-jを入れて、中断する。

未完成。

# Main

