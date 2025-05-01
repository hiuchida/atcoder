# Main
n,kを読み込み、int[] aryに読み込む。
TreeSet<Long> setを用意し、set.add(0L+ary[i])を追加する。

k回ループし、v=set.pollFirst()を取り出し、
iを0からn回ループし、set.add(v+ary[i])に追加する。

set.first()を出力する。
