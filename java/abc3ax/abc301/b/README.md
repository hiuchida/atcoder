# Main
nを読み込み、int[] aryに読み込む。
MyArray maを用意し、ma.add(ary[0])を追加する。
iを1からn-1までループし、
abs(ary[i]-ary[i-1])>1の場合、d=0を用意し、
ary[i]>ary[i-1]の場合、d=1を入れる。
それ以外は、d=-1を入れる。
jをary[i-1]+dからj!=ary[i]まで+dループし、ma.add(j)を追加する。
ma.add(ary[i])を追加する。
abs(ary[i]-ary[i-1])<=1の場合、ma.add(ary[i])を追加する。

ma.join(" ")を出力する。
