# Main01
ax,ay,bx,by,cx,cyを読み込む。
bax=ax-bx、bay=ay-by、bcx=cx-bx、bcy=cy-by、dotBaBc=calc_dot(bax, bay, bcx, bcy)を入れる。
cax=ax-cx、cay=ay-cy、cbx=bx-cx、cby=by-cy、dotCaCb=calc_dot(cax, cay, cbx, cby)を入れる。

ans=0を用意し、
dotBaBc<0の場合ans=calc_dist(ax, ay, bx, by)を入れる。
dotCaCb<0の場合ans=calc_dist(ax, ay, cx, cy)を入れる。
それ以外はcrossBaBc=calc_cross(bax, bay, bcx, bcy)、distBc=calc_dist(bx, by, cx, cy)、crossBaBc/distBcを入れる。
ansを出力する。

calc_dotの中で、内積を計算する。

calc_crossの中で、外積を計算する。

calc_distの中で、距離を計算する。
WA11個。

# Main
Main01を元に、
long ans=(long)x1\*x2+y1\*y2;
を
long ans=(long)x1\*x2+(long)y1\*y2;
に修正。

long ans=(long)x1\*y2-y1\*x2;
を
long ans=(long)x1\*y2-(long)y1\*x2;
に修正。
AC 72ms

