# Main
abc168\_cをコピー

a,b,h,mを読み込み、h%=12を入れる。
h1=h/12.0\*360.0+m/60.0/12.0\*360
m1=m/60.0\*360
h2=h1/360\*2\*Math.PI
m2=m1/360\*2\*Math.PI
hy=a*Math.cos(h2)
hx=a*Math.sin(h2)
my=b*Math.cos(m2)
mx=b*Math.sin(m2)を入れ、
calc_dist(hx, hy, mx, my)を出力する。

calc_distの中で、2点の距離を返す。
