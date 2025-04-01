# Main
n,mを読み込み、List<Integer> listを用意する。
dfsを呼び出す。

dfsの中で、list.size()==nならばlistの全要素を出力して中断する。
jをi+1からmまでループし、
listにjを追加し、dfs(j)を呼び出し、listの末尾を取り除く。
