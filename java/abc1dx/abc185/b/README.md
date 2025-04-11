# Main
n,m,tを読み込み、int[] aa,abに読み込む。
x=n、st=0を用意し、iを0からm回ループし、
a=aa[i]、d=aa[i]-stを用意し、x<=dの場合ng。
x-=d、x+=ab[i]-aa[i]、x=min(x, n)、st=ab[i]を入れる。

d=t-stを用意し、x<=dの場合ng。
それ以外はok。
