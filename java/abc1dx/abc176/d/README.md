# Main
h,w,ch,cw,dh,dwを読み込み、Maze mzに読み込む。
st=Point(ch, cw, 1)、ed=Point(dh, dw, 0)を用意する。
Deque<Point> queを用意し、queの先頭にstを追加し、map[st.y][st.x]=st.sを入れる。
queが空になるまでループし、
p=que.poll()に取出し、
dを0からDY.length回ループし、
y=p.y+DY[d]、x=p.x+DX[d]、s=p.sを入れる。
check(y, x, s)ならばqueの先頭にPoint(y, x, s)を追加し、map[y][x]=sを入れる。
dy,dxを-2から2まで2重ループし、
y2=p.y+dy、x2=p.x+dx、s2=p.s+1を入れる。
check(y2, x2, s2)ならばqueの末尾にPoint(y2, x2, s2)を追加し、map[y2][x2]=s2を入れる。
ループが終了したら、map[ed.y][ed.x]-1を出力する。
