# Main01,Main02
橋をTreeMap<Integer,TreeSet<Integer>> mapに登録する。
スタックを使って、グラフを巡回する。
Main01でTLEとなったので、Main02で一度通った橋を削除する。
TLE6個。TLE4個。

# Main
橋をTreeMap<Integer,TreeSet<Integer>> mapに登録する。
スタックを使って、グラフを巡回する。
TreeSet<Integer> doneがメモリを圧迫するので、boolean\[\] doneに置き換える。

# Main\_dfs
dfsメソッドを再帰呼び出しする。
TLE1個。
