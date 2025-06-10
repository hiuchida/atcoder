# Main01
Main01との比較。
ax<bx && ax<cx || ax>bx && ax>cxの場合ng。
ax<bx && cx<ax || ax>bx && ax<cxの場合ng。
WA25個。

# Main02
Main03との比較。
cross1==cross2の場合、ax<bx && ax>cx || ax>bx && ax<cxの場合ng。
cross1==cross2の場合、ax<bx && ax>cx || ax>bx && ax<cxの場合ng。
WA46個。

# Main03
Main11との比較。
cross1==0 && cross1==cross2の場合、ax<bx && ax>cx || ax>bx && ax<cxの場合ng。
cross1==0 && cross1==cross2の場合、ax<bx && ax>cx || ax>bx && ax<cxの場合ng。
WA46個。

# Main11
Main12との比較。
cross1*cross2>0の場合ng。
cross3*cross4>0の場合ng。
その後にcross1==0 && cross2==0 && cross3==0 && cross4==0の判定。
WA2個。

# Main12
Main13との比較。
(cross1!=0 || cross2!=0) && cross1*cross2>=0の場合ng。
(cross3!=0 || cross4!=0) && cross3*cross4>=0の場合ng。
その後にcross1==0 && cross2==0 && cross3==0 && cross4==0の判定。
WA54個。

# Main13
Main14との比較。
cross1>=0 && cross2>=0、cross1<=0 && cross2<=0、cross3>=0 && cross4>=0、cross3<=0 && cross4<=0の場合ng、それ以外はok。
WA54個。

# Main14
Main15との比較。x1==x2,x2==x3,x3==x4の場合y座標を判定していない。
max(x1, x3)<=min(x2, x4)の場合ok、それ以外はng。
WA2個。

# Main15
Main16との比較。大小比較の際に、rtx,rtyにminではなくmaxを入れていた。
x2<x4の場合rtx=x4、rty=y4を入れる。
x2>x4の場合rtx=x2、rty=y2を入れる。
それ以外はrtx=x2、rty=min(y2, y4)を入れる。
WA38個。

# Main16
Main31との比較。
x1<x3の場合ltx=x3、lty=y3を入れる。
x1>x3の場合ltx=x1、lty=y1を入れる。
それ以外はltx=x3、lty=max(y1, y3)を入れる。

x2<x4の場合rtx=x2、rty=y2を入れる。
x2>x4の場合rtx=x4、rty=y4を入れる。
それ以外はrtx=x2、rty=min(y2, y4)を入れる。

ltx<rtxの場合ok
ltx>rtxの場合ng
lty<=rtyの場合ok
それ以外はng
WA27個。

# Main16_fix
Main16の
x1>x2の場合x1とx2をスワップ、y1とy2をスワップする。
x3>x4の場合x3とx4をスワップ、y3とy4をスワップする。
の部分が、x1==x2やx3==x4の場合に、y1,y2やy3,y4がスワップされない。
AC 73ms

# Main31
x1,y1,x2,y2,x3,y3,x4,y4を読み込む。
ax=x2-x1、ay=y2-y1、bx=x3-x1、by=y3-y1、cx=x4-x1、cy=y4-y1を入れる。
cross1=ax\*by-ay\*bx、cross2=ax\*cy-ay\*cxを入れる。
ax=x4-x3、ay=y4-y3、bx=x1-x3、by=y1-y3、cx=x2-x3、cy=y2-y3を入れる。
cross3=ax\*by-ay\*bx、cross4=ax\*cy-ay\*cxを入れる。

cross1==0 && cross2==0 && cross3==0 && cross4==0の場合、
x1>x2の場合x1とx2をスワップ、y1とy2をスワップする。
x3>x4の場合x3とx4をスワップ、y3とy4をスワップする。
x1==x2 && x2==x3 && x3==x4の場合max(y1, y3)<=min(y2, y4)の場合ok、それ以外はng。
max(x1, x3)<=min(x2, x4)の場合ok、それ以外はng。

bab=false、bcd=falseを用意し、
cross1>=0 && cross2<=0またはcross1<=0 && cross2>=0の場合bab=trueを入れる。
cross3>=0 && cross4<=0またはcross3<=0 && cross4>=0の場合bcd=trueを入れる。
bab && bcdの場合ok、それ以外はng。
WA27個。

# Main
Main31の
x1>x2の場合x1とx2をスワップ、y1とy2をスワップする。
x3>x4の場合x3とx4をスワップ、y3とy4をスワップする。
の部分が、x1==x2やx3==x4の場合に、y1,y2やy3,y4がスワップされない。

x1,y1,x2,y2,x3,y3,x4,y4を読み込む。
ax=x2-x1、ay=y2-y1、bx=x3-x1、by=y3-y1、cx=x4-x1、cy=y4-y1を入れる。
cross1=ax\*by-ay\*bx、cross2=ax\*cy-ay\*cxを入れる。
ax=x4-x3、ay=y4-y3、bx=x1-x3、by=y1-y3、cx=x2-x3、cy=y2-y3を入れる。
cross3=ax\*by-ay\*bx、cross4=ax\*cy-ay\*cxを入れる。

cross1==0 && cross2==0 && cross3==0 && cross4==0の場合、
x1>x2の場合x1とx2をスワップ、y1とy2をスワップする。
x1==x2のかつy1>y2の場合y1とy2をスワップする。
x3>x4の場合x3とx4をスワップ、y3とy4をスワップする。
x3==x4かつy3>y4の場合y3とy4をスワップする。
x1==x2 && x2==x3 && x3==x4の場合max(y1, y3)<=min(y2, y4)の場合ok、それ以外はng。
max(x1, x3)<=min(x2, x4)の場合ok、それ以外はng。

bab=false、bcd=falseを用意し、
cross1>=0 && cross2<=0またはcross1<=0 && cross2>=0の場合bab=trueを入れる。
cross3>=0 && cross4<=0またはcross3<=0 && cross4>=0の場合bcd=trueを入れる。
bab && bcdの場合ok、それ以外はng。
AC 77ms

