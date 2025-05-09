# Main
nを読み込み、int[] iaに読み込む。
Counter cを用意し、c.add(ia[i])を追加する。
ans = 0を用意し、c.keySet()の要素oをループし、
key = (Integer) o、cnt = c.get(o)を入れ、
key > cntの場合、ans += cntを入れ、
key < cntの場合、ans += cnt - keyを入れる。
AC 227ms

# Main\_fix
書き直す。

nを読み込み、int[] aryに読み込む。
Counter cntを用意し、cntにary[i]を追加する。

ans=0を用意し、cntの要素kでループし、v=cnt.get(k)に入れる。
v<kならば、ans+=vを加え、v>kならば、ans+=v-kを加える。
ループ終了したら、ansを出力する。
AC 506ms
