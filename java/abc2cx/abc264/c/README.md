# Main01
h1,w1を読み込み、int[][] ary1に読み込む。
h2,w2を読み込み、int[][] ary2に読み込む。
h1<h2 || w1<w2のときは、小さいのでng。
TreeSet<Point> set1にary1[i][j]==ary2[0][0]となる座標を追加する。
TreeSet<Point> set2にary1[i][j]==ary2[h2-1][w2-1]となる座標を追加する。
set1.size()==0 || set2.size()==0のときは、左上と右下が存在しないのでng。
set1とset2の全要素でループし、p1.y>p2.y || p1.x>p2.xはスキップする。
check(p1, p2)で、チェックを行う。

check内で、チェック方法を迷って断念。

# Main


