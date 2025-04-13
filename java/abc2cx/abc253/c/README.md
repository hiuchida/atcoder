# Main01
qを読み込み、Counter cntを用意する。
iを0からq-1までループし、cmdを読み込む。
cmd==1ならばxをcntにカウントする。
cmd==2ならばv=cnt.get(x)でカウントを取得し、del=min(c, v)をカウントから引く。
cmd==3ならばcnt.map.keySet()がTreeSet<Integer>にキャストできないため、
TreeSet<Integer>のインスタンスを生成する。
setの最小値と最大値を得て、差を出力する。
TLE4個。

# Main
Main01を元に、TreeSet<Integer>のインスタンスを生成せずに、
Countの中にMap<Integer, Integer> mapとTreeSet<Integer> setの両方を持たせて、
put()の中で同時に更新する。
cmd==3のときに、cnt.setから直接set.first()とset.last()を呼び出す。

# Main\_fix
Counter_int_int20250413を適用
TreeSet<Integer> set=cnt.set　を　NavigableSet<Integer> set=cnt.keySet()　に変更。

