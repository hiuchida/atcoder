# Main01
x1,y1,r1,x2,y2,r2を読み込む。
r1>r2の場合、(x1,y1,r1)と(x2,y2,r2)をスワップする。
rr1=(long)r1\*r1、rr2=(long)r2\*r2、rr12=(long)(r1+r2)\*(r1+r2)、dist=calc_dist(x1, y1, x2, y2)を入れる。
rr1+dist<rr2の場合1を出力する。
rr1+dist==rr2の場合2を出力する。
rr12>distの場合3を出力する。
rr12==distの場合4を出力する。
rr12<distの場合5を出力する。

calc_distの中で、2点の距離の2乗を返す。

WA19個。

# Main
Main01を元に、
r1+dist<r2の場合1を出力する。
r1+dist==r2の場合2を出力する。
r1+r2>distの場合3を出力する。
r1+r2==distの場合4を出力する。
r1+r2<distの場合5を出力する。

calc_distの中で、2点の距離を返す。
AC 73ms

