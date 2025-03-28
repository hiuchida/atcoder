# Main
n,wを読み込み、int ary\[\]に読み込む。
TreeSet<Integer> setを用意し、
iを0からn-1までループし、sum1=ary[i]を入れ、sum1<=wならばsetに追加する。
jをi+1からn-1までループし、i==jはスキップする。
sum2=sum1+ary[j]を入れ、sum2<=wならばsetに追加する。
kをj+1からn-1までループし、i==kまたはj==kはスキップする。
sum3=sum2+ary[k]を入れ、sum3<=wならばsetに追加する。
3重ループが終了したら、setのサイズを出力する。
