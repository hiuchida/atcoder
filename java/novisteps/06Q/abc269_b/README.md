# Main01
n=10を用意し、String[] aryに読み込む。
lt=0、tp=0を用意し、y,xを0からn-1までループして、ary[y].charAt(x)=='#'の場合、lt=x+1,tp=y+1を入れて、
ループを終了させるためx=n,y=nを入れる。
ループが終了したら、rt=n、bm=nを用意し、
yをtpからn-1までループして、ary[y].charAt(lt)=='.'の場合、bm=yを入れて中断する。（見つからない場合は初期値のn）
xをltからn-1までループして、ary[tp].charAt(x)=='.'の場合、rt=xを入れて中断する。（見つからない場合は初期値のn）
tp,bmとlt,rtをそれぞれ出力する。
WA4個。RE4個。

# Main
Main01を元に、

if (ary[y].charAt(lt)=='.') {
を
if (ary[y].charAt(lt-1)=='.') {
に修正。

if (ary[tp].charAt(x)=='.') {
を
if (ary[tp-1].charAt(x)=='.') {
に修正。

最後に出力する1baseに変えてしまったので、0baseの座標系ではWAやREになる。
最後に+1すればよかった。

解説を読んで、1<=a,b,c,d<=10をすべて試すでも間に合うので、
わざわざ凝ったことをしなくてもよいかと。

