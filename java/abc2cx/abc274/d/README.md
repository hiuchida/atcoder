# Main
n,x,yを読み込み、int[] aryに読み込む。

TreeSet<Integer> setを用意し、ary[0]を追加する。
iを2からn-1まで+2ループする。
TreeSet<Integer> set2を用意し、setの全要素をjに入れ、
set2にj+ary[i]とj-ary[i]を追加する。
setにset2を入れて、ループの先頭に戻る。
setにxが存在しない場合ng。

TreeSet<Integer> setを用意し、0を追加する。
iを1からn-1まで+2ループする。
TreeSet<Integer> set2を用意し、setの全要素をjに入れ、
set2にj+ary[i]とj-ary[i]を追加する。
setにset2を入れて、ループの先頭に戻る。
setにyが存在しない場合ng。
