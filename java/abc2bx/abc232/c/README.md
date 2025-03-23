# Main
n,mを読み込む。
TreeSet<Point> setabにPoint(a, b)を追加する。
TreeSet<Point> setcdにPoint(c, d)を追加する。
int[] aryとboolean[] flagを用意し、dfs(ary, 0, flag)を呼び出す。
dfsが終了したらng。

dfs内で、int[] aryに1からnまでの順列を全列挙する。
iがary\[\]の添字、flag\[\]に選択済のフラグを持たせる。
jを1からnまでループし、flag\[j-1\]が未選択ならばary\[i\]=jを入れ、i+1のdfs()を再帰呼び出しする。
iがnまで達したら、check()を呼ぶ。

check内で、setcdを元に、各Point(c, d)をary\[\]によって置き換えたary[p.st-1]とary[p.ed-1]の2つを計算し、
c<dとなるPoint(c, d)をsetに追加する。
setabとsetを比較し、同一ならばok。
