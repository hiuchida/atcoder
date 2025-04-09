# Main
dfsで総当たりするが、初回srch()と1歩以降dfs()が被っている。

# Main_dfs
srch()で4方向に動かず、0歩目としてdfs(y,x,k,set);を呼ぶ。

# Main_remove
dfs()で4方向へ移動する際TreeSet<Point> set1からset4までコピーを取っているため、
set.add(p);に対してset.remove(p);で元に戻す。

Main_dfsとMain_removeの比較
実行時間 1567ms -> 801ms
メモリ 74332KB -> 69472KB
