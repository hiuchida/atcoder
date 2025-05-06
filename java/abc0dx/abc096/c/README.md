# Main
h,wを読み込み、Bitmap mapを用意し、
s.charAt(x - 1) == '#'の場合、map.set(x, y, true)をセットする。
y,xを1からh,wまでループし、check(map, x, y)の場合ng。
ループ終了したらok。

checkの中で、
!map.is(x, y)の場合falseを返す。
map.is(x - 1, y)の場合falseを返す。
map.is(x + 1, y)の場合falseを返す。
map.is(x, y - 1)の場合falseを返す。
map.is(x, y + 1)の場合falseを返す。
それ以外は、trueを返す。
AC 123ms

# Main
r26からr31に変更。
map.readLines('#')に置き換え。
map.count4(x, y) == 0の場合falseを返す。
AC 81ms

# Main\_fix
Maze mzに読み込む。
y,xを1からh,wまでループし、
mz.map[y][x]<0の場合、bok=falseを用意し、
dを0からDY.length回ループし、mz.map[y+DY[d]][x+DX[d]]<0の場合、bok=trueをセットする。
!bokの場合、ng。
AC 83ms

