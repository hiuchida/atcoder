# Main
n,mを読み込み、String[] aryに読み込む。
boolean[] flag、MyArray maを用意し、dfs(0, flag, ma)を呼び出し、ng。

dfsの中で、
i==nの場合okで、終了する。
nxtを0からn-1までループし、flag[nxt]の場合スキップする。
ma.size()>0の場合、pre=ma.peek()を入れ、!check(pre, ary[nxt])の場合スキップする。
flag[nxt]=true、ma.add(ary[nxt])を入れ、dfs(i+1, flag, ma)を呼び出し、
flag[nxt]=false、ma.remove()を戻す。

checkの中で、
cnt=0を用意し、iを0からa.length()回ループし、
a.charAt(i)!=b.charAt(i)の場合cnt++をカウントする。
cnt<=1を返す。
