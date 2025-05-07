# Main
n,kを読み込み、Counter cntを用意し、cnt.inc(a)を追加する。
cnt.size()<=kの場合、0を出力して、終了する。

int[] ary、idx=0を用意し、cnt.keySet()の要素keyでループし、ary[idx++]=cnt.get(key)を入れる。
aryをソートする。

ans=0を用意し、iを0からary.length-k回ループし、ans+=ary[i]を加える。
ansを出力する。
AC 727ms
