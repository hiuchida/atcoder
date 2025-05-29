# Main01
n,k,lを読み込み、UnionFind uf,uf2、byte[] flgを用意し、
k回ループし、uf.merge(ia[0], ia[1])を連結し、flg[ia[0]] |= 1、flg[ia[1]] |= 1を入れる。
l回ループし、uf2.merge(ia[0], ia[1])を連結し、flg[ia[0]] |= 2、flg[ia[1]] |= 2を入れる。

iを1からnまでループし、i != 1の場合" "を出力する。
flg[i] != 3の場合"1"を出力し、スキップする。
cnt = 1を用意し、jを1からnまでループし、
i != jの場合、uf.same(i, j)の場合、uf2.same(i, j)の場合、cnt++をカウントする。
cntを出力する。
TLE12個。

# Main
n,k,lを読み込み、UnionFind uf,uf2に連結する。
Map<Point,Integer> mapを用意し、iを1からnまでループし、
pt = new Point(uf.root(i), uf2.root(i))、v = map.get(pt)を入れ、
v == nullの場合map.put(pt, 1)を追加し、
それ以外はmap.put(pt, v+1)を追加する。

iを1からnまでループし、
pt = new Point(uf.root(i), uf2.root(i))、v = map.get(pt)を入れ、
vを出力する。
AC 632ms

# Main2
Map<Object,Integer> mapを持つCounterクラスを用意する。
Counter cを用意し、pt = new Point(uf.root(i), uf2.root(i))を入れ、c.add(pt)に追加する。

pt = new Point(uf.root(i), uf2.root(i))、v = c.get(pt)を入れ、vを出力する。
AC 608ms

# Main\_fix
書き直す。

Mainと同じMap<Point, Integer> mapを用意して解いた。
AC 1034ms

