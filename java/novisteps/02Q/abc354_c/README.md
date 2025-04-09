# Main01
TreeSet<Point> set1,TreeSet<Point2> set2,TreeSet<Point3> set3を用意し、
それぞれソートキーはst,ed,idxでソートされる。
set1とset2に入力データを読み込み、set1の強さの降順に取り出す。
最高の強さのカードは消えないので、set1とset2から取り除き、set3へ追加する。
set2のコストの大きい降順に取り出し、p1のコストよりp2のコストが大きいものを
すべてset1とset2から取り除き、コストが小さくなった時点で中断する。
set2が空になるまでループする。
set1が空になるまでループする。
set3のidxを出力する。

set3.sizeを出力し忘れて、WA20個。

# Main
TreeSet<Point> set1,TreeSet<Point2> set2,TreeSet<Point3> set3を用意し、
それぞれソートキーはst,ed,idxでソートされる。
set1とset2に入力データを読み込み、set1の強さの降順に取り出す。
最高の強さのカードは消えないので、set1とset2から取り除き、set3へ追加する。
set2のコストの大きい降順に取り出し、p1のコストよりp2のコストが大きいものを
すべてset1とset2から取り除き、コストが小さくなった時点で中断する。
set2が空になるまでループする。
set1が空になるまでループする。
set3のidxを出力する。

# Main\_fix
TreeSet<Point> set1は、stをソートキーとするComparator<Point>を定義する。
TreeSet<Point2> set2は、edをソートキーとするComparator<Point>を定義する。
TreeSet<Point3> set3は、TreeSet<Integer> set3に置き換える。
set1とset2のPointは共通なため、removeする際にp1とp2をそのまま指定できる。

# Main\_final
Pointクラス内にnewComparator1()等を追加する。

