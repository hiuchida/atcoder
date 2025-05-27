# Main01
n,sを読み込み、int[] aryに読み込む。
iを0から2^n回ループし、sum=0を用意する。
jを0からn回ループし、mask=1 << jを用意し、(i&mask)>0の場合、sum+=ary[j]を加える。
sum==sの場合、ok。
ループ終了したら、ng。
WA5個TLE4個。

# Main
本文 2.4.6 項の回答のため、TLEは許容するが、WAはコードの問題。
n=60のとき、2^60はint型で扱えないため、int iとint maskをlong iとlong maskに変更する。
また、1 << nは(int)1 << nの定数のため2^31までしか正しい値でないので、1L << nに変更する。
TLE9個。（Subtask2の中で2個ACしたのは、Yesが見つかると終了するため）

# Main\_dp01
動的計画法で書き直し。

n,sを読み込み、int[] aryに読み込む。
boolean[][] dpを用意し、dp[0][0]=trueを入れる。
iを0からn-1までループし、jを0からs-1までループし、!dp[i][j]の場合スキップする。
dp[i+1][j]=trueを入れる。
j+ary[i]<=sの場合dp[i+1][j+ary[i]]=trueを入れる。

dp[n][s]の場合ok、それ以外はng。
WA5個。

# Main\_dp
Main\_dp01を元に、
ans=falseを用意し、iを0からnまでループし、dp[i][s]の場合ans=trueを入れる。
ansの場合ok、それ以外はng。
AC 83ms

# Main\_dp01\_fix
Main\_dp01のdp\[n\]\[s\]を判定するのは間違っていないが、dp\[i\]\[s\]を更新していなかった。
jを0からs-1までループし、
を
jを0からsまでループし、
に修正。
AC 77ms

