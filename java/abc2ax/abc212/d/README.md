# Main1
クエリ2でbaseを足すので、クエリ1はx-baseを登録する。
クエリ3で最小値にbaseを足して出力する。
WA14個。

# Main
同じ数字が重複することが抜けていた。
TreeSetからTreeMapに変更し、valueに個数を追加する。
クエリ1でmapに存在しなければ1で追加し、存在すれば+1を更新する。
クエリ3でカウントを-1し、0なら削除する。
AC 935ms

# Main_PriorityQueue
重複し、昇順にソートされているデータ構造として、PriorityQueueに書き換える。
カウントは不要なので短く書ける。
AC 779ms

# Main_MyHeapque
MyHeapque_long20250428適用
AC 817ms

# Main_MyHeapque2
MyHeapque_long20250504適用
AC 778ms

