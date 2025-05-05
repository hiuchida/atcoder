# Main
h,wを読み込み、Counter[] cntを用意する。
yを0からh回ループし、cnt[y]=new Counter()を用意する。
xを0からw回ループし、cnt[y].add(a, x)に追加する。

ans=0を用意し、iを0から2^h回ループし、
List<Counter> listを用意し、jをh回ループし、
mask=1 << jを入れ、(i&mask)>0の場合、list.add(cnt[j])を追加する。
x=calc(list)を呼び出し、ansをxの最大値で更新する。

ansを出力する。

calcの中で、ans=0を用意し、
list.size()==0の場合、0を返す。
list.size()==1の場合、
list.get(0).keySet()の要素vでループし、ansをlist.get(0).size(v)の最大値で更新する。
ansを返す。
list.size()>1の場合、
list.get(0).keySet()の要素vでループし、Counter cntを用意し、
jを0からlist.size()回ループし、c=list.get(j)を入れ、
c.get(v)の要素uでループし、cnt.add(u, j)に追加する。
x=0を用意し、cnt.keySet()の要素uでループし、
cnt.get(u).size()==list.size()の場合、x++をカウントする。
ansをx*list.size()の最大値で更新する。
ansを返す。
