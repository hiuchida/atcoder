# Main
a,b,c,x,yを読み込み、ans=LONG\_MAXを用意する。
abを0からmax(x, y)\*2まで+2ループする。
a1=(long)ab*c、x1=x-ab/2、y1=y-ab/2を入れ、
x1<0の場合x1=0を入れる。
y1<0の場合y1=0を入れる。
a2=(long)a\*x1、a3=(long)b\*y1、a4=a1+a2+a3を入れる。
ansをa4の最小値に更新する。

ansを出力する。
