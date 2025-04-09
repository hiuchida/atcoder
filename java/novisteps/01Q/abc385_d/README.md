# Main0
TLEの付いたソース。
縦方向、横方向にそれぞれListで持っているので、
同じx座標の縦方向、同じy座標の横方向が、複数重なるとListが膨れるので逐次検索となる。
重なる領域を結合しようとして、時間切れ。

# Main
解説の方針。
Mapで管理するのは、サンタの移動でなく、家を入れる。
Map<Long, TreeSet<Long>> mapxがx座標をkeyにしたy座標のset。
Map<Long, TreeSet<Long>> mapyがy座標をkeyにしたx座標のset。
chkx()では効率よくx1からx2の範囲を検索するため、
x1<x2とした状態でv = ceiling(x1)がv != nullでv<=x2の場合に
回答をカウントし、mapxとmapyの両方から取り除く。

# Main_notnull
getx()とgety()が常にNotNullを返すことで、nullチェックを省く。


後から確認して、
Long v = sy.ceiling(x1);が見つかった場合、x1=v;した方がよいかと思ったが、
vも取り除いているのでx1のままであまり変わらない。

chkxとchkyでx1<x2となるように交換しているが、c>0のため、"D"と"L"は呼び出し元で
反対にしてもよい。
