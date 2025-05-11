# Main01
nを読み込み、
List<Point> lr,lbに読み込み、
p1.x == p2.x && p1.y == p2.yの場合0、p1.x < p2.x && p1.y < p2.yの場合-1、それ以外は1でソートする。
ans = 0を用意し、iをn - 1から0まで-1ループし、
r = lr.get(i)を用意し、jをlb.size() - 1から0まで-1ループし、
b = lb.get(j)を用意し、
pc.compare(r, b) < 0の場合、ans++、lr.remove(i)、lb.remove(j)を取り除き、中断する。
ansを出力する。
WA2個RE6個。

# Main02
Main01を元に、
lr.size() ==0の場合、中断する。
WA2個RE6個。

# Main03
Main01を元に、
iをn - 1から0まで-1ループし、
を
iをlr.size() - 1から0まで-1ループし、
に修正。
WA2個RE6個。

# Main04
Main03を元に、
iをlr.size() - 1から0まで-1ループし、
を
iをlb.size() - 1から0まで-1ループし、
に修正。

jをlb.size() - 1から0まで-1ループし、
を
jをlr.size() - 1から0まで-1ループし、
に修正。
WA2個RE6個。

# Main05
Main04を元に、
lr.remove(i)、lb.remove(j)を取り除き
を
r.x = 999、r.y = 999を入れる。
に修正。
WA2個RE6個。

# Main06
Main05を元に、
lr,lbをソートする。
を削除。
WA8個。

# Main07
Main06を元に、
r1 = p1.x * p1.x + p1.y * p1.y
r2 = p2.x * p2.x + p2.y * p2.y
r1 == r2の場合0、r1 < r2の場合-1、それ以外は1でソートする。
WA8個。

# Main08
Main07を元に、
r.x = 999、r.y = 999を入れる。
を
lr.remove(i)、lb.remove(j)を取り除き
に修正。
WA8個。

# Main09
Main08を元に、
lr,lbをソートする。
を削除。

dfs(new ArrayList<>(lr), new ArrayList<>(lb))を出力する。

dfsの中で、
lr.size() == 0の場合、0を返す。
r = lr.get(0)、max = 0を用意し、
jを0からlb.size()回ループし、b = lb.get(j)、ans = 0を用意し、
pc.compare(r, b) < 0の場合、ans++をカウントする。
List<Point> nlr、nlbを用意し、nlr.remove(0)、nlb.remove(j)を取り除き、
ans += dfs(nlr, nlb)を呼び出し、max < ansの場合、max = ansを入れる。
maxを返す。
TLE12個。

# Main10
Main09を元に、
pc.compare(r, b) >= 0の場合スキップする。
WA2個TLE7個。

# Main
nを読み込み、
List<Point> lrに読み込み、y座標を降順にソートする。
List<Point> lbに読み込み、x座標を降順にソートする。
ans = 0を用意し、iを0からlb.size()回ループし、b = lb.get(i)を入れる。
jを0からlr.size()回ループし、r = lr.get(j)を入れる。
r.x < b.x && r.y < b.yの場合、ans++、lr.remove(j)を取り除き、中断する。

ansを出力する。
AC 80ms
AC 88ms

# Main\_fix
書き直す。
AC 99ms

