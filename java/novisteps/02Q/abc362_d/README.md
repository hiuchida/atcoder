# Main
n,mを読み込み、int[] aryに読み込む。
Counter cntを用意し、m回ループし、u,v,bを読み込み、cntへ(u,v,b)と(v,u,b)を追加する。
long[] ansを用意し、ans\[0\]=0（未使用）、ans\[1\]=ary\[1\]で初期化する。
bfs(1)を呼び出し、1から全探索する。
iを2からnまでループし、ans\[i\]を出力する。

bfs内で、Deque<Integer> queを用意し、iを追加する。
queが空になるまでループする。
iにqueから先頭を受け取り、xにans\[i\]を入れる。
cnt.get(i)のすべてのPair pをループし、
j=p.idxを入れ、x2=x+p.w+ary[j]を入れる。
x2が最小値となる場合、ans[j]を更新し、queにjを追加する。

# Main\_fix
PairをBeanに変更。

