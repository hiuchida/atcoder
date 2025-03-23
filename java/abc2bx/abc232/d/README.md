# Main01
h,wを読み込み、Maze mzを構築する。
bfsでansを求め、ansを出力する。

bfsでDeque<Point> queを用意し、new Point(1, 1, 1)を追加する。
queが存在するまでループし、1件取出し、p.d>ansならばansを更新する。
p.y+1とp.x+1の2点が空いていれば、queへ追加する。
TLE3個。

# Main
h,wを読み込み、Maze mzを構築する。
bfsでansを求め、ansを出力する。

bfsでDeque<Point> queを用意し、new Point(1, 1, 1)を追加する。
queが存在するまでループし、1件取出し、p.d>ansならばansを更新する。
p.y+1とp.x+1の2点が空いていれば、queへ追加する。
queへ追加した時点で、mz.mapにも1を入れ、同じ座標が複数回queに追加しないようにする。

