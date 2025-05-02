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
縦と横のbit探索で作り直し。

int[][] ary1,ary2に読み込む。

iを0から2^h1回ループし、bitCount(i)!=h2の場合スキップする。
int[][] ary3、idx=0を用意し、
jを0からh1回ループし、mask=1 << jを用意し、
(i&mask)>0の場合、ary1[j]をary3[idx]にコピーし、idx++を進める。
check(ary3, ary2)ならばok。
ループ終了したらng。

checkの中で、
iを0から2^w1回ループし、bitCount(i)!=w2の場合スキップする。
idx=0、bok=trueを用意し、
jを0からw1回ループし、mask=1 << jを用意し、
(i&mask)>0の場合、kを0からh2回ループし、
ary3[k][j]!=ary2[k][idx]の場合、bok=falseをリセットする。
idx++を進める。
bokならばtrueを返す。
ループ終了したらfalseを返す。

