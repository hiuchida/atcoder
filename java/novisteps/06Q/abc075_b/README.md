# Main
h,wを読み込み、Maze mzに読み込む。
y,xを1からh,wまでループし、map[y][x]<0の場合スキップする。
cnt=0を用意し、dを0からDY.length回ループし、
yy=y+DY[d]、xx=x+DX[d]を入れ、checkrange(yy, xx)かつmap[yy][xx]<0の場合、cnt++をカウントする。
dループ終了したら、map[y][x]=cntを更新する。

y,xを1からh,wまでループし、v=mz.map[y][x]を入れる。
v<0ならば'#'を出力し、それ以外はvを出力する。

