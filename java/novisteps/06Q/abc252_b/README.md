# Main
n,kを読み込み、int[] aryに読み込む。
max=0と初期化し、max<ary[i]ならばmaxを更新する。
TreeSet<Integer> setを用意し、bを追加する。
iを0からn-1までループし、max==ary[i]のときに、
setにi+1が存在する場合ok。
ループが終了したらng。
