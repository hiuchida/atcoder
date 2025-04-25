# Main
nを読み込み、int[][] aa,abに読み込む。
iを0から4回ループし、comp(n, aa, ab)を呼び出し、trueの場合ok。
ac=rotate(n, aa)を呼び出し、aa=acに入れる。
ループ終了したらng。

rotateの中で、int[][] ansを用意し、
y,xを0からn-1までループし、ans[y][x]=ary[n-1-x][y]を入れる。
ansを返す。

compの中で、
y,xを0からn-1までループし、
aa[y][x]==0の場合スキップする。（最初、常にaa[y][x]!=ab[y][x]していたが、1のときのみ判定する）
aa[y][x]!=ab[y][x]の場合falseを返す。
ループ終了したらtrueを返す。
