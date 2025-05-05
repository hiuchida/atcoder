# Main01
nを読み込み、int[][] daに読み込む。
Aggregate2D aggを用意し、daで初期化する。
qを読み込み、q回ループし、
pを読み込み、ans = 0、prey = -1を用意する。
xを(p >= n) ? n : pからx>0まで-1ループし、
y = n / xを入れ、prey == yの場合スキップする。
prey = yを入れ、
ltを0からlt + x <= nまでループし、
tpを0からtp + y <= nまでループし、
ansをagg.sum(lt, tp, lt + x, tp + y)の最大値に更新する。
ansを出力する。
WA33個。

# Main
Main01を元に、
y = n / xを入れ
を
y = Math.min(n, p / x)を入れ
に修正。

# Main\_fix
Aggregate2D20250505を適用

