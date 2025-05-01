# Main01
【方針】
x軸を奥行き、y軸を左右、z軸を上下とする。
円の頂点が(0,0,L/2)となり、
e==0のとき(0,0,0)のため、y=0、z=-L/2のため270°の位置。
e==T/4のとき(0,-L/2,L/2)のため、y=-L/2、z=0のため180°の位置。
同様にe==T/2のとき、y=0、z=L/2のため90°の位置。
同様にe==3T/4のとき、y=L/2、z=0のため0°の位置。

e==0を0°とすると、z軸がcos、y軸がsinとなり、時計回りのためsinの符号が反対になる。
y=-L/2\*sinθ、z=L/2-L/2\*cosθ、θ=2π\*E/T

俯角はatan2(H,W)のため、H=z、W=sqrt(X^2+(Y-y)^2)


t,l,x,y,qを読み込み、q回ループし、eを読み込み、calc(t, l, x, y, e)を出力する。

calcの中で、
rad=2\*PI\*e/t
my=-l\*sin(rad)/2
mz=-l\*cos(rad)/2+l/2
xx=x
yy=y-my
w=sqrt(xx\*xx+yy\*yy)
rad2=atan2(mz, w)
ans=rad2\*360/2/Math.PI
ansを返す。
WA7個。

# Main02
ans=BigDecimal.valueOf(ans).toPlainString()で指数表示(1.23E-4等)を抑制する。
WA7個。

# Main
calcの引数をすべてdouble型にする。
おそらくmzのl/2が切り捨てられている可能性がある。

# Main02\_fix
Main02を元に、
double mz=-l*Math.cos(rad)/2+l/2
を
double mz=-l*Math.cos(rad)/2+l/2.0
に修正。

