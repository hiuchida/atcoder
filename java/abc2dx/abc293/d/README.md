# Main
n,mを読み込み、UnionFind uf、TreeSet<Integer> setを用意する。
iを0からm回ループし、a,b,c,dを読み込み、
uf.same(a, c)の場合、r=uf.root(a)を入れ、setにrを追加する。
uf.merge(a, c)する。

TreeSet<Integer> set2を用意し、iを1からnまでループし、
r=uf.root(i)を入れ、setにrが存在する場合スキップする。
set2にrが存在する場合スキップする。
set2にrを追加する。

set.sizeとset2.sizeを出力する。
