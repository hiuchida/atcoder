# Main
h,w,sy,sx,gy,gxを読み込み、Maze mzに読み込む。
Deque<Point> queを用意し、que.add(new Point(sx, sy, 1))を追加し、mz.map[sy][sx]=1を入れる。

queが空になるまでループし、p1=que.poll()を取り出し、
dを0からDY.length回ループし、
ex=p1.x+DX[d]、ey=p1.y+DY[d]を入れ、
mz.map[ey][ex]==0の場合、mz.map[ey][ex]=p1.d+1を入れ、que.add(new Point(ex, ey, p1.d+1))を追加する。

mz.map[gy][gx]-1を出力する。
