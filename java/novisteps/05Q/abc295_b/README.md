# Main
h,wを読み込み、Maze mzに読み込む。
y,xを1からh,wまでループし、v=mz.map[y][x]を入れる。
v>0の場合、dy,dxを-vからvまでループし、
calc(0, 0, dx, dy)<=vの場合、ny=y+dy、nx=x+dxを入れ、
checkrange(ny, nx)の場合、mz.map[ny][nx]<0の場合、mz.map[ny][nx]=0に置換する。

y,xを1からh,wまでループし、mz.map[y][x]<0ならば'#'、それ以外は'.'を出力する。

calcL1()適用
