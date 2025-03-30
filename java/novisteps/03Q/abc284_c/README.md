# Main
n,mを読み込み、UnionFind ufに(u,v)と(v,u)を結合する。
iを1からnまでループし、TreeSet<Integer> setにuf.root(i)を追加する。
set.sizeを出力する。

# Main\_fix
解説のソースを見て手直し。
(u,v)と(v,u)をmergeしているが、a->bの有向木を扱っていたときの癖。
ufは(u,v)のみでよい。

TreeSet<Integer> setにuf.root(i)を追加してカウントを取っているが、
i==uf.root(i)でカウントが取れる。

