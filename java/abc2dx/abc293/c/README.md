# Main
h,wを読み込み、int[][] aryに読み込む。
ans=0、Set<Integer> setを用意し、dfs(0, 0, set)を呼び出し、ansを出力する。

dfsの中で、v=ary[y][x]を入れ、setにvが存在する場合、戻る。
y==h-1 && x==w-1の場合、ans++をカウントして、戻る。
setにvを追加する。
x+1<wの場合、dfs(y, x+1, set)を呼び出す。
y+1<hの場合、dfs(y+1, x, set)を呼び出す。
setからvを取り除く。
戻る。
