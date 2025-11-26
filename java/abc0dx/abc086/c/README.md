# Main
nを読み込み、t = 0、x = 0、y = 0を用意する。
n回ループし、ti,xi,yiを読み込み、
dt = abs(ti - t)、dx = abs(xi - x)、dy = abs(yi - y)を入れ、dt < dx + dyの場合ng。
dt -= dx + dyを入れ、dt % 2 == 1の場合ng。
t = ti、x = xi、y = yiを入れる。
ループ終了したらok。
AC 274ms

# Main\_fix
書き直す。

nを読み込み、mx=0、my=0、mt=0を用意する。
n回ループし、t,x,yを読み込み、
d=calc(mx, my, x, y)、dt=t-mtを入れ、d>dtの場合ng。
dt-=dを入れ、dt%2==1の場合ng。
mx=x、my=y、mt=tを入れる。
ループ終了したらok。
AC 440ms

calcL1()適用
