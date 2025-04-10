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

# Main02
Main01を元に、
que.addするタイミングでmz.map[p.y][p.x]を更新する。
WA15個。TLE7個。

# Main03
Main02を元に、
p.y==ed.y && p.x==ed.xのときに中断せずに、
ループが終わったときにmap[ed.y][ed.x]-1を出力する。
WA15個。TLE7個。

# Main04
Main03を元に、
PriorityQueue<Point> queをDeque<Point> queに変更。
que.add(st)をque.addFirst(st)に変更。
mz.map[st.y][st.x]=st.pを追加。
mz.map[p.y][p.x]!=0 && mz.map[p.y][p.x]<p.pのチェックを削除。
mz.map[p.y][p.x]=p.pを削除。
que.add(new Point(y, x, p.p))をque.addFirst(new Point(y, x, p.p))に変更。
que.add(new Point(y+DY[dd], x+DX[dd], p.p+1))をque.addLast(new Point(y+DY[dd], x+DX[dd], p.p+1))に変更。
que.add(new Point(y2+DY[dd], x2+DX[dd], p.p+1))をque.addLast(new Point(y2+DY[dd], x2+DX[dd], p.p+1))に変更。
WA17個。

# Main05
Main04を元に、
y=p.y+DY[d]、x=p.x+DX[d]
を
y=p.y、x=p.x、y1=y+DY[d]、x1=x+DX[d]、s=p.p
に修正。
mz.check(y, x, p.p)のときmz.map[p.y][p.x]=p.p
を
mz.check(y1, x1, s)のときmz.map[y1][x1]=s
に修正。
WA15個。

# Main06
Main05を元に、
mz.range(y1, x1) && mz.map[y1][x1]<0
を
mz.range(y1, x1)
に修正。
mz.range(y2, x2) && mz.map[y2][x2]<0
を
mz.range(y2, x2)
に修正。
WA15個。

# Main07
Main06を元に、
check(y2, x2, s+1)のときque.addLast(new Point(y2, x2, s+1));mz.map[y2][x2]=s+1;を追加。
WA15個。

# Main08
Main07を元に、
que.addFirstを先に4方向すべて処理してから、que.addLastを処理する。
Pointのint pをint sに変更。
WA15個。

# Main09
Main08を元に、
mz.check(y1, x1, s)の後に、mz.check(y2, x2, s)もチェックする。
WA15個。

# Main10
Main09を元に、
int[] DY2,DX2を用意し、que.addLastを1つのループで書き直す。
WA15個。

# Main
Main10を元に、
Maze mz.mapとは別に、int\[\]\[\] distに前蹴り回数+1を保持する。
mapのみで上手くいかない理由は、壁-1のときに、壁の前蹴り回数を保持できないため。
distの初期値0のまま開始するので、スタート地点を1として、ゴールの値から-1する。
1歩先で道はsをqueの前に追加、1歩先で壁はs+1をqueの後に追加
2歩先はs+1をqueの後に追加

1歩先、2歩先ともに道は、1歩先が道のqueが先に処理され、さらに1歩先が道のqueが優先される。

# Main\_TestData
h=5,w=5で2^25のパターンで比較したら、i=36078のビットパターンでMain01とMainの差異が見つかった。
以下のように、2歩先の上下左右が壁のときに、2回キックすれば壁が壊せるはずが、ゴールまでたどり着けていない。
5 5
.###.
###..
##...
#....
.....
1 1 5 5

