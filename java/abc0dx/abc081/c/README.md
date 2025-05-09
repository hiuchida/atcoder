# Main01
n,kを読み込み、int[] ibに読み込む。
Counter cを用意し、c.add(ib[i])に追加する。
List<Info> lを用意し、c.keySet()の要素oをループし、
key = (Integer) oを入れ、l.add(new Info(key, c.get(o)))に追加する。
lをソートする。
ans = 0を用意し、l.size() > kの間ループし、
ans += l.get(0).valを加え、l.remove(0)を取り除く、
ansを出力する。
TLE1個。

# Main
Main01を元に、
lをソートし、反転する

ans = 0を用意し、l.size() > kの間ループし、
ans += l.get(l.size() - 1).valを加え、l.remove(l.size() - 1)を取り除く。
AC 353ms

# Main\_fix
書き直す。

n,kを読み込み、Counter cntを用意し、cnt.inc(a)を追加する。
cnt.size()<=kの場合、0を出力して、終了する。

int[] ary、idx=0を用意し、cnt.keySet()の要素keyでループし、ary[idx++]=cnt.get(key)を入れる。
aryをソートする。

ans=0を用意し、iを0からary.length-k回ループし、ans+=ary[i]を加える。
ansを出力する。
AC 727ms
