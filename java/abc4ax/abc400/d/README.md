# Main01
h,wを読み込み、Maze mzに読み込む。
a,b,c,dを読み込み、st=Point(a, b, 1)、ed=Point(c, D, 1)を用意する。
PriorityQueue<Point> queを用意し、queにstを追加する。
queが空になるまでループし、
p=que.poll()に取出し、p.y==ed.y && p.x==ed.xならばp.p-1を出力して終了する。
mz.map[p.y][p.x]!=0 && mz.map[p.y][p.x]<p.pならばスキップする。
mz.map[p.y][p.x]=p.pを更新する。
dを0からDY.length回ループし、
y=p.y+DY[d]、x=p.x+DX[d]、y2=y+DY[d]、x2=x+DX[d]を入れる。
check(y, x, p.p)ならばqueに(y, x, p.p)を追加する。
range(y, x) && mz.map[y][x]<0の場合、ddを0からDY.length回ループし、
check(y+DY[dd], x+DX[dd], p.p+1)ならばqueに(y+DY[dd], x+DX[dd], p.p+1)を追加する。
range(y2, x2) && mz.map[y2][x2]<0の場合、ddを0からDY.length回ループし、
check(y2+DY[dd], x2+DX[dd], p.p+1)ならばqueに(y2+DY[dd], x2+DX[dd], p.p+1)を追加する。
WA8個。TLE17個。

# Main

