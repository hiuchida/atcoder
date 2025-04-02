# Main
nを読み込み、Point[] aryに読み込む。TreeSet<Point> setに追加する。
UnionFind ufを用意する。
iを0からn-1までループし、
x1=ary[i].x、y1=ary[i].yに入れる。
dを0からDY.length-1までループし、
x=x1+DX[d]、y=y1+DY[d]を入れる。
(x, y)がsetに存在する場合、merge(x1, y1, x, y)する。
ループが終了したら、ans=0を用意する。
iを0からn-1までループし、
x=x1+DX[d]、y=y1+DY[d]、u=x+y*uf.wを入れ、u==uf.root(u)の場合ans++にカウントする。
ループが終了したらansを出力する。
