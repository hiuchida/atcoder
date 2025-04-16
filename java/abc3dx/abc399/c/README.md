# Main
n,mを読み込み、(u,v)と(v,u)をCounter cntに追加する。
boolean[] flagとgrp=0を用意し、
iを1からnまでループし、flag\[i\]==falseの場合にdfsを呼び出し、grp++する。
ループが終了したら、m-(n-grp)を出力する。

dfs内で、flag[i]=trueし、cnt.get(i)のすべての要素でループし、
flag[nxt]==falseの場合にdfsを呼び出す。

# Main\_uf
dfsを使わず、UnionFindでグループ化し、親rootをTreeSetに入れてサイズを得る。

# Main\_bfs
bfsで書き直す。

abc288/cと同じだが、dfsだとabc288/cはTLEとなる。
再帰呼び出しのコストがかかっている可能性があるので、bfsでも作る。

