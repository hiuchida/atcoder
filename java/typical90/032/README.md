# Main
nを読み込み、int[][] aryに読み込む。
mを読み込み、Counter cntを用意し、
m回ループし、x,yを読み込み、cnt.add(x, y)、cnt.add(y, x)を追加する。

boolean[] flag、MyArray maを用意し、dfs(0, 0, flag, ma)を呼び出し、
ans==INT\_MAXの場合ans=-1を入れる。
ansを出力する。

dfsの中で、
i==nの場合、ansをvの最小値を更新し、戻る。
TreeSet<Integer> setを用意し、
ma.size()>0の場合、pre=ma.peek()、set=cnt.get(pre+1)を入れる。
nxtを0からn-1までループし、flag[nxt]の場合スキップする。
set.contains(nxt+1)の場合スキップする。
flag[nxt]=true、ma.add(nxt)を入れ、dfs(i+1, v+ary[nxt][i], flag, ma)を呼び出す。
flag[nxt]=false、ma.remove()を戻す。
