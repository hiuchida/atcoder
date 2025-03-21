# Counter
abc369/cより共通化
static classとしてMain内部に貼り付ける。
Map<Integer, Integer> mapを使って、int cの個数を数える。
dec,subを追加し、カウントが0のときにremoveする。

# Counter\_s
abc231/bより共通化
static classとしてMain内部に貼り付ける。
Map<String, Integer> mapを使って、int cの個数を数える。
dec,subを追加し、カウントが0のときにremoveする。

# Maze
abc387/dより共通化
static classとしてMain内部に貼り付ける。
\[h+2\]\[w+2\]を確保し、壁を-1、通路'.'を0とする。
'S'は28、'G'は16として、デバッグ出力すると、SGが出力される。

# MyDeque
typical90/061より共通化
static classとしてMain内部に貼り付ける。
ArrayDeque<Integer>で十分使えるが、get(int idx)がないため、
バッファサイズが固定で、addFirst,addLast,getのみ提供する。

# PairL
abc349/dより共通化
static classとしてMain内部に貼り付ける。
Point\_stedとほぼ同じで、変数型をintからlongに変更した。

# Point
abc383/bより共通化
static classとしてMain内部に貼り付ける。
TreeSet<Point>とHashSet<Point>のどちらでも使える。

# Point_s
abc386/dより共通化
static classとしてMain内部に貼り付ける。
Pointは(y,x)座標しか保持しないが、ソートに影響しない属性としてString sを追加する。

# Point_sted
abc380/cより共通化
static classとしてMain内部に貼り付ける。
Point(y,x)からPoint(st,ed)へ、変数名を変えた。

# Point_stedidx
abc354/cより共通化
static classとしてMain内部に貼り付ける。
Point(st,ed)からPoint(st,ed,idx)へ、変数名を変えた。

# Prime
abc383/dより共通化
static classとしてMain内部に貼り付ける。

# staticFields
static変数としてMain内部に貼り付ける。
原則、staticメソッドを必要とするものはクラス化する。

# staticMethods
staticメソッドとしてMain内部に貼り付ける。
原則、static変数を必要とするものはクラス化する。

# UnionFind
atc001/bより共通化
static classとしてMain内部に貼り付ける。
経路圧縮とサイズによる連結を実装したもの。

## UnionFind_ltrt
UnionFindに左端と右端を持たせる。
mergeは連続していることを前提に使う。
本来、low/highやfloor/ceilingかもしれない。
