# Main01
h,wを読み込み、String[] aryに読み込む。
x=1、y=1、bdone=falseを用意する。
!bdoneの間ループし、
ary[y-1].charAt(x-1)の文字が
'U'の場合、y==1のときbdone=true、それ以外はy++
'D'の場合、y==hのときbdone=true、それ以外はy--
'L','R'の場合も、xで同様
y==1 && x==1ならばスタート地点なので-1を出力し終了する。
ループが終了したらy,xを出力する。
WA1個。TLE4個。

# Main
Main01を元に、boolean[][] flagを用意する。
flag[y-1][x-1]のとき-1を出力し終了する。
それ以外はflag[y-1][x-1]=trueをセットする。
y==1 && x==1の判定を削除する。

