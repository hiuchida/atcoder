# Main
List<Point> lrに読み込み、y座標を降順にソートする。
List<Point> lbに読み込み、x座標を降順にソートする。
ans = 0を用意し、iを0からlb.size()回ループし、b = lb.get(i)を入れる。
jを0からlr.size()回ループし、r = lr.get(j)を入れる。
r.x < b.x && r.y < b.yの場合、ans++、lr.remove(j)を取り除き、中断する。

ansを出力する。
AC 88ms

# Main\_fix
書き直す。
AC 99ms

