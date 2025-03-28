# Main
sを読み込む。
TreeSet<Integer> setl,setuを用意する。
iを0からs.length-1までループし、
iの文字が小文字ならばsetlに存在する場合ng。存在しない場合setlに追加する。
大文字ならばsetuに存在する場合ng。存在しない場合setuに追加する。
ループが終了し、setlとsetuのどちらかのsize==0の場合ng。
それ以外はok。
