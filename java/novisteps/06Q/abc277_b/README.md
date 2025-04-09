# Main
nを読み込み、Set<String> setを用意する。
iをn回ループし、sを読み込み、check(s)を呼び出す。
setにsが存在する場合ng。それ以外はsetにsを追加する。
iループ終了したらok。

checkの中で、1文字目をch1、2文字目をch2を入れる。
ch1=='H' || ch1=='D' || ch1=='C' || ch1=='S'の場合に、
ch2=='A' || ch2=='T' || ch2=='J' || ch2=='Q' || ch2=='K'の場合、戻る。
'2'<=ch2 && ch2<='9'の場合、戻る。
それ以外はng。
