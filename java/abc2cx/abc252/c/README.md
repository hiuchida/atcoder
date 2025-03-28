# Main
nを読み込み、String[] aryに読み込む。
ansにINT\_MAXを初期化する。
iを0から9までループする。
calc()を呼び出し、戻り値でansの最小値を更新する。
ループが終了したらansを出力する。

calc()内で、char chにiの文字を入れる。
TreeSet<Integer> setを用意する。
ary\[\]の中のsをループし、sの中からchの位置idxを探す。
setにidxが存在する場合ループし、idxを+10する。
ループが終了したら、setにidxを追加する。
setの最大値を返す。
