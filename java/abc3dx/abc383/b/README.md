# Main0
WAの付いたソース。
おそらく無意識に効率化のため、if (y == u) continue;とif (x == v) continue;を別々に判定している。

# Main
25分かかりif (y == u && x == v) continue;に修正

# Main_static
メソッド呼び出しの引数を減らすため、入力データint h, w, d;とboolean[][] map;をstatic変数化。
if (y1 < 0 || y1 > h)やif (x1 < 0 || x1 > w)が座標0は番兵の外壁なのでif (y1 < 1 || y1 > h)に変更。
d>1の場合もあるため、外壁に番兵を置く価値は無いが、mapの座標を0baseとすると常に-1することとなり、
1baseとすると座標0は番兵（もしくは不定値でも可）となる。デバッグ出力したときの見栄えのため、座標h+1や座標w+1も番兵を置く。

# Main_point
TreeSet<Integer>をTreeSet<Point>にする。
Integerにy*1000+xを入れていたため、Pointの第1ソートキーをy、第2ソートキーをxとする。
PointはcompareTo()の実装が必要。

# Main_hash
TreeSet<Point>をHashSet<Point>にする。
Pointはequals()とhashCode()の実装が必要。
ハッシュ値で並ぶので、デバッグ出力が[(1, 2), (3, 4), (1, 4), (1, 3), (2, 4)]のようにバラバラとなる。
