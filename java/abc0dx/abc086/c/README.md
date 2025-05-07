# Main
nを読み込み、mx=0、my=0、mt=0を用意する。
n回ループし、t,x,yを読み込み、
d=calc(mx, my, x, y)、dt=t-mtを入れ、d>dtの場合ng。
dt-=dを入れ、dt%2==1の場合ng。
mx=x、my=y、mt=tを入れる。
ループ終了したらok。
AC 440ms
