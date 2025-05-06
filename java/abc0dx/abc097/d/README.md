# Main01
n,mを読み込み、int[] pに読み込む。
PointList pl、Bitmap mapを用意する。
iを0からm回ループし、x,yを読み込み、pl.add(x, y)、map.set(x, y)、map.set(y, x)を追加する。

ans = 0、List<Integer> listを用意し、
iを1からnまでループし、p[i - 1] == iの場合、ans++をカウントする。
それ以外は、list.add(i)を追加する。

listの要素vをループし、boolean[] flgを用意し、
dfs(n, v, p[v - 1], map, flg)の場合、ans++をカウントする。

ansを出力する。

dfsの中で、
v == dの場合、trueを返す。
flg[v]の場合、falseを返す。
flg[v] = trueを入れ、iを1からnまでループし、v == iの場合スキップする。
map.is(v, i)の場合、d == iの場合、trueを返す。
dfs(n, i, d, map, flg)の場合、trueを返す。
ループ終了したら、falseを返す。
RE6個。

# Main02
Main01を元に、
List<Integer> listを削除。
iを1からnまでループし、boolean[] flgを用意し、
dfs(n, i, p[i - 1], map, flg)の場合、ans++をカウントする。
RE6個。

# Main03
Main02を元に、
PointList pl、Bitmap mapを削除。
node = map.get(x)を入れ、node == nullの場合、node = new Node()を入れ、map.put(x, node)を追加する。
node.l.add(y)を追加する。
node = map.get(y)を入れ、node == nullの場合、node = new Node()を入れ、map.put(y, node)を追加する。
node.l.add(x)を追加する。

dfs(n, i, p[i - 1], flg)を呼び出す。

dfsの中で、
node = map.get(v)を入れ、node == nullの場合、falseを返す。
node.lの要素iをループし、
d == iの場合、trueを返す。
dfs(n, i, d, flg)の場合、trueを返す。

Nodeクラスは、List<Integer> lを持つ。
TLE3個。

# Main
Main03を元に、
UnionFind ufを用意する。
uf.merge(x, y)を連結する。
uf.same(i, p[i - 1])の場合、ans++をカウントする。
AC 352ms

# Main\_fix
UnionFind20250102を適用。
AC 464ms

