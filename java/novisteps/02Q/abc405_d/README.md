# Main
h,wを読み込み、Maze mzに読み込む。
Deque<Point> queを用意し、mz.lstの要素pをループし、que.add(p)に追加する。
que.size()>0の間ループし、
p=que.poll()、s=mz.map[p.y][p.x]を用意し、
dを0からDY.length回ループし、
y=p.y+DY[d]、x=p.x+DX[d]を用意し、
mz.map[y][x]<0やmz.map[y][x]!=0の場合スキップする。
mz.map[y][x]=s+1を入れ、que.add(new Point(y, x))を追加する。

y,xを1からh,wまでループし、i=mz.map[y][x]を用意し、
i<0の場合、'#'を出力する。
i==1の場合、'E'を出力する。
それ以外の場合、dを0からDY.length回ループし、
y2=y+DY[d]、x2=x+DX[d]、i2=mz.map[y2][x2]を入れ、
i-1==i2の場合、DS.charAt(d)を出力する。
