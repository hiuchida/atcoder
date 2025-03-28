# Main
n,ax\[\],ay\[\]を読み込む。
sを読み込み、sが'L'ならmaplへ追加、それ以外はmaprへ追加する。
maplのキーでループし、maprに存在しない場合はスキップ。
それ以外はcheck()を呼び出す。
ループが終了したらng。

add()の中で、map.get(y)が存在しない場合、setを用意し、mapへ追加する。
setにxを追加する。

check()の中で、mapl.get(y)でループし、lxより小さい値がmapr.get(y)に見つかればok。

