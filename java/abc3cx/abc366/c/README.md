# Main0
問題を勘違い。
TreeSet<Integer>にあるなしを管理して、種類数を出力したが、
同じ数が2個以上入ることを考慮していなかった。

# Main
TreeMap<Integer,Integer> mapに書き換える。
クエリ１について、map.get()がnullの場合、1を追加する。
map.get()が数値の場合、+1を更新する。
クエリ２について、map.get()の数値を-1し更新する。
0となった場合、取り除く。
クエリ３について、map.size()が1個以上の種類数。
