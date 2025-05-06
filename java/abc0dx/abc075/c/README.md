# Main
n,mを読み込み、Bitmap mapに読み込む。
ans = 0を用意し、iを1からnまでループし、jをi+1からnまでループし、
map.is(i, j)の場合、
map.set(i, j, false)、map.set(j, i, false)を入れ、boolean[] flgを用意し、
!dfs(flg, map, i, j)の場合、ans++をカウントする。
map.set(i, j, true)、map.set(j, i, true)を戻す。
ansを出力する。

dfsの中で、
flg[i] = trueを入れ、kを1からflg.length-1までループし、i == kの場合スキップする。
j == k && map.is(i, k)の場合、trueを返す。
map.is(i, k) && !flg[k]の場合、dfs(flg, map, k, j)の場合、trueを返す。
ループ終了したら、falseを返す。

# Main\_fix
UnionFindで書き直す。

int[][] aryに読み込む。
ans=0を用意し、iを0からm回ループし、UnionFind ufを用意する。
jを0からm回ループし、i==jの場合スキップする。
uf.merge(ary[j][0], ary[j][1])を連結する。

cnt=0を用意し、jを1からnまでループし、j==uf.root(j)の場合、cnt++をカウントする。
cnt>1の場合、ans++をカウントする。

ansを出力する。

