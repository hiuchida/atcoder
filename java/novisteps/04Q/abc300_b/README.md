# Main
h,wを読み込み、String[] aa,abに読み込む。
s,tを0からh,w回ループし、ac=shift(aa, s, t)を呼び出し、comp(ac, ab)の場合ok。
ループ終了したらng。

shiftの中で、String[] ansを用意し、
yを0からh回ループし、s=ary[(y+sy)%h]を入れ、char[] acを用意する。
xを0からw回ループし、ac[x]=s.charAt((x+sx)%w)を入れる。
ループ終了したら、ans[y]=new String(ac)を入れる。
ループ終了したら、ansを返す。

compの中で、yを0からh回ループし、
!aa[y].equals(ab[y])の場合falseを返す。
ループ終了したら、trueを返す。
