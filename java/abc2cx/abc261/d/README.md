# Main
n,mを読み込み、int[] aryに読み込む。
TreeMap<Integer,Integer> mapを用意し、mapに(c, y)を追加する。
long[][] dpを用意する。
jを0からn-1までループし、iを0からn-1までループし、i>jと反転している場合はスキップする。
裏の場合：dp[0][j+1]=Math.max(dp[0][j+1], dp[i][j])を更新する。
bp=map.get(i+1)を得て、bp==nullのときはbp=0を入れる。
表の場合：dp[i+1][j+1]=Math.max(dp[i+1][j+1], dp[i][j]+ary[j]+bp)を更新する。
ループが終了したら、ans=0を用意する。
iを0からnまでループし、dp[i][n]の最大値をansに更新する。
ansを出力する。

# Main\_dfs
dfsで書き直してみる。
long[][] dpとans=0を用意する。
iをnから0まで-1ループして、dfs(i, n)の最大値をansに更新する。

dfsの中で、j==0ならば再帰呼び出しを中断する。
キャッシュdp[i][j]>0があれば、キャッシュを返す。
i>0の場合、表の場合を計算する。
v=dfs(i-1, j-1)を呼び出す。
bp=map.get(i)を得て、bp==nullのときはbp=0を入れる。
v+=ary[j-1]+bp;に加える。
キャッシュdp[i][j]=vにセットして、vを返す。
i==0の場合、裏の場合を計算する。
hを0からj-1までループし、v=dfs(h, j-1)を呼び出す。
vの最大値を更新して、キャッシュdp[i][j]=maxにセットして、maxを返す。

