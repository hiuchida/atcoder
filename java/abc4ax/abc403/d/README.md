# Main01
n,dを読み込み、Counter cntを用意し、cnt.inc(a)にカウントする。
List<Integer> list、ans=0を用意し、listの要素keyでループし、
v1=cnt.get(key)を入れ、v1==0の場合スキップする。
cnt.put(key, 0)を置き換える。
v2=cnt.get(key+d)を入れ、v2==0の場合cnt.put(key, 0)を置き換えてスキップする。
cnt.put(key+d, 0)を置き換える。
iを2から無限に+2ループし、
v11=cnt.get(key+i*d)を入れ、v11==0の場合中断する。
cnt.put(key+i*d, 0)を置き換える。
v1+=v11を加える。
v21=cnt.get(key+(i+1)*d)を入れ、v21==0の場合中断する。
cnt.put(key+(i+1)*d, 0)を置き換える。
v2+=v21を加える。
ループ終了したら、
v1<v2の場合ans+=v1、それ以外はans+=v2を加える。
ループ終了したら、ansを出力する。
WA20個。

# Main02
Main01を元に、リストに追加して動的計画法で答えを計算する。
v1=cnt.get(key)を入れ、v1==0の場合スキップする。
cnt.put(key, 0)を置き換える。
List<Integer> lstを用意し、lst.add(v1)を追加する。
iを1から無限ループし、v2=cnt.get(key+i*d)を入れ、
v2==0の場合中断する。
cnt.put(key+i*d, 0)を置き換える。
lst.add(v2)を追加する。
ループ終了したら、ans+=calc(lst)を加える。

calcの中で、
int[][] dpを用意し、INT\_MAXで初期化する。
dp[0][0]=0、dp[1][0]=0を入れる。
iを0からlst.size()回ループし、
dp[0][i+1]にdp[1][i]の最小値を更新する。
dp[1][i+1]にdp[0][i]+lst.get(i)の最小値を更新する。
dp[1][i+1]にdp[1][i]+lst.get(i)の最小値を更新する。
min(dp[0][lst.size()], dp[1][lst.size()])を返す。
WA5個。

# Main03
Main02を元に、
dp[0][0]=0
を
dp[0][0]=(int)1e8
に修正。
WA5個。

# Main
d==0の条件を見逃していた。
Main03を元に、
d==0の場合、n-cnt.size()を出力する。

