# Main01
n,mを読み込み、int[] aa,ab、Point[] ap、Set<Point> setを用意し、
iを0からm回ループし、aa[i],ab[i]に読み込み、p=Point(aa[i], ab[i])を用意し、
ap[i]=pを入れ、set.add(p)を追加する。

cnt=0を用意し、iを0からm回ループし、!set.contains(ap[i])の場合スキップする。
set.remove(ap[i])を取り除く。
jをi+1からm-1までループし、
check(n, ap[i], ap[j])を呼び出し、trueの場合、cnt++にカウントし、set.remove(ap[j])を取り除く。

ans=m*(m-1)/2を入れ、ans-=cntを更新し、ansを出力する。

checkの中で、
i=p1.st、j=p1.ed、k=p2.st、l=p2.edを入れ、i==k || i==l || j==k || j==lの場合falseを返す。
i>kの場合、k+=nを入れる。
j<lの場合、j+=nを入れる。
d1=(k-i)%n、d2=(j-l)%nを入れ、d1==d2の場合trueを返す。
それ以外はfalseを返す。
WA10個。TLE16個。

# Main02
Main01を元に、d1==d2の判定の後に追加する。
i>lの場合、l+=nを入れる。
j<kの場合、j+=nを入れる。
d3=(l-i)%n、d4=(j-k)%nを入れ、d3==d4の場合trueを返す。
WA10個。TLE16個。

# Main


