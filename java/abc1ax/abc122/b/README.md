# Main01
sを読み込み、char[] aryに展開する。
ans=0を用意し、ltを0からn-1までループし、
rtをltからn-1までループし、
check(ary, lt, rt)を呼び出し、trueならばansをrt-ltの最大値に更新する。
ansを出力する。

checkの中で、
iをltからrt-1までループし、
ary[i]!='A' && ary[i]!='C' && ary[i]!='G' && ary[i]!='T'の場合falseを返す。
ループ終了したら、trueを返す。
WA5個。

# Main
Main01を元に、
rtをltからn-1までループし、
を
rtをltからnまでループし、
に変更。

n=7、lt=0のとき、rt<nでは最後のrt=7が抜ける。
0 0 
0 1 A
0 2 AT
0 3 ATC
0 4 ATCO
0 5 ATCOD
0 6 ATCODE
0 7 ATCODER

