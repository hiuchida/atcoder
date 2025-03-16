# Main
long[][] aryを-1で初期化し、ary\[u\]\[v\]とary\[v\]\[u\]にwを設定する。
dfsでidx=1からidx=nまで探索する。

# Main\_flag
List<Integer> listに訪問履歴を持たせる代わりに、
boolean[] flagに訪問フラグを持たせる。
