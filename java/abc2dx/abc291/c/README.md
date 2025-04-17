# Main01
n,sを読み込み、char[] aryに展開する。
TreeSet<Point> set、x=0、y=0を用意し、setにPoint(x, y)を追加する。
iを0からn回ループし、
idx=DS.indexOf(ary[i])、nx=x+DX[idx]、ny=y+DY[idx]を入れ、
p=new Point(nx, ny)を用意し、setにpが存在する場合ok。
x=nx、y=nyを入れ、ループの先頭に戻る。
ループ終了したらng。
WA12個。

# Main
Main01を元に、setにpが存在する場合okの後に、
set.add(p)
を追加。

