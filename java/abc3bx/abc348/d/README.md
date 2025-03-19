# Main01
Maze mzを読み込み、Point st,edを入れ、Map<Point,Integer> mapに薬の位置を入れる。
Deque<Point> queにstを追加する。
queが空になるまでループする。
queから1件取り出し、mapに存在し、エネルギーが増える場合は増やす。
ゴールならば打ち切る。
エネルギーが1以上ならば、上下左右の移動可能な位置をqueに追加する。
TLE24個。

# Main02
Main01のPointのComparableをエネルギーeの降順に変更し、
mapのComparatorを(y, x)の昇順にする。
Maze mzを読み込み、Point st,edを入れ、Map<Point,Integer> mapに薬の位置を入れる。
PriorityQueue<Point> queにstを追加する。
queが空になるまでループする。
queから1件取り出し、mapに存在し、エネルギーが増える場合は増やす。
ゴールならば打ち切る。
エネルギーが1以上ならば、上下左右の移動可能な位置をqueに追加する。
TLE22個。

# Main03
Main02の前に、エネルギーを無視して、ゴールまで到達可能か調べた。
TLE28個。

