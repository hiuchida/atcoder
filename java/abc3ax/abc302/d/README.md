# Main01
n,m,dを読み込み、long[] aryに読み込む。
TreeSet<Long> setを用意し、set.add(sc.nextLong())に追加する。

ans=-1を用意し、aryの要素vをループし、
v1=v+d、v2=set.floor(v1)を入れ、v2==nullの場合スキップする。
v1-d<=v2の場合、ansにv+v2の最大値を更新する。

ansを出力する。
WA21個。

# Main
Main01を元に、
v1-d<=v2の場合
を
v-d<=v2の場合
に修正。

