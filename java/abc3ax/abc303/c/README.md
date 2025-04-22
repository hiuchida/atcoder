# Main01
n,m,h,k,sを読み込み、char[] aryに展開し、Set<Point> setを用意する。
iを0からm回ループし、x,yを読み込み、setにPoint(x, y)を追加する。

x=0、y=0を用意し、iを0からn回ループし、h--を更新し、
ary[i]=='R'の場合x++を更新する。
ary[i]=='L'の場合x--を更新する。
ary[i]=='U'の場合y++を更新する。
ary[i]=='D'の場合y--を更新する。
h<0の場合ng。
setにPoint(x, y)が存在し、h<kの場合、h=kを更新する。
ループ終了したらok。
WA4個。

# Main
Main01を元に、
h<kの場合、h=kを更新し、set.remove(p)を取り除く。

