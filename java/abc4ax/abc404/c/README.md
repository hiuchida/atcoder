# Main
n,mを読み込み、int[] aryを用意し、ary[a]++、ary[b]++をカウントする。
UnionFind ufを用意し、uf.merge(a, b)に連結する。
iを1からnまでループし、ary[i]!=2の場合ng。
uf.size(1)==nの場合ok。それ以外はng。
AC 565ms
