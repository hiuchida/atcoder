# Main
n個を読み込み、Counter cntを用意する。
n回ループし、aを読み込み、cnt.inc(a)をカウントする。

ans=0、List<Integer> keysを用意し、
keysの要素keyをループし、
c1=cnt.get(key)を入れ、c1==0の場合スキップする。
key2=100000-keyを入れ、
key==key2の場合、ans+=calc_c(c1, 2)を入れる。
それ以外の場合、c2=cnt.get(key2)を入れ、c2==0の場合スキップする。
ans+=(long)c1*c2を入れる。
cnt.sub(key, c1)を取り除く。

ansを出力する。

calc_cの中で、
ans=1を用意し、
iを0からk回ループし、ans*=n-iを入れる。
iを1からkまでループし、ans/=iを入れる。
ansを返す。
