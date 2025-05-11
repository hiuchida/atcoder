# Main01
nを読み込み、Counter cntを用意し、cnt.inc(a)にカウントする。
int[] ary、idx=0を用意し、idx<Nの間ループし、
cnt.size()==0の場合、0を出力して、終了する。
key=cnt.keySet().last()、val=cnt.get(key)を入れ、
val>=2の場合、ary[idx++]=keyを入れる。
cnt.sub(key, val)を減算する。（mapから削除する）

ans=ary[0]、ans*=ary[1]を入れ、ansを出力する。
WA3個。

# Main
正方形も含むことを考慮していなかった。

Main01を元に、
ans2=0を用意する。

cnt.size()==0の場合、0を出力して、終了する。
を
cnt.size()==0の場合、max(0, ans2)を出力して、終了する。
に修正。

val>=4の場合、x=key、x*=xを入れ、ans2をxの最大値に更新する。

ans=ary[0]、ans*=ary[1]を入れ、ansを出力する。
を
ans1=ary[0]、ans1*=ary[1]を入れ、max(ans1, ans2)を出力する。
に修正。
AC 548ms

