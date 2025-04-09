# Bean\_long
abc278/dより共通化
static classとしてMain内部に貼り付ける。
MapのValueに入れることを前提にする。
Map<Integer, Integer>ではValueを更新する度にラッパークラスを作り直すが、
Map<Integer, Bean>ならばBeanの中を更新できる。

# Counter\_i
abc369/cより共通化
static classとしてMain内部に貼り付ける。
Map<Integer, Integer> mapを使って、int kの個数を数える。
更新時、カウントが0のときにremoveする。

# Counter_int_setint
abc278/cより共通化
static classとしてMain内部に貼り付ける。
Map<Integer, TreeSet<Integer>> mapを使って、int kに関連したint idxを管理する。

# Counter\_l
abc233/dより共通化
static classとしてMain内部に貼り付ける。
Map<Long, Integer> mapを使って、long kの個数を数える。
更新時、カウントが0のときにremoveする。

# Counter\_list
abc248/dより共通化
static classとしてMain内部に貼り付ける。
Map<Integer, List<Integer>> mapを使って、int kのint idxをリストに追加する。
リストは追加のみで削除できないため、mapの要素もremoveされない。

# Counter\_s
abc231/bより共通化
static classとしてMain内部に貼り付ける。
Map<String, Integer> mapを使って、String kの個数を数える。
更新時、カウントが0のときにremoveする。

# Label
abc128/bより共通化
static classとしてMain内部に貼り付ける。

# Maze
abc387/dより共通化
static classとしてMain内部に貼り付ける。
\[h+2\]\[w+2\]を確保し、壁を-1、通路'.'を0とする。
'S'は28、'G'は16として、デバッグ出力すると、SGが出力される。
## Maze250408
abc075/bより共通化
checkrange()、check()追加

# MyDeque\_i
# MyDeque\_l
typical90/061より共通化
static classとしてMain内部に貼り付ける。
ArrayDeque<Integer>で十分使えるが、get(int idx)がないため、
バッファサイズが固定で、addFirst,addLast,getのみ提供する。

# PairL
abc349/dより共通化
static classとしてMain内部に貼り付ける。
Point\_stedとほぼ同じで、変数型をintからlongに変更した。

# Point_sted
Point(st,ed)
abc380/cより共通化
static classとしてMain内部に貼り付ける。

# Point_stedidx
Point(st,ed,idx) PK(st,ed)
abc354/cより共通化
static classとしてMain内部に貼り付ける。
Point(st,ed)からPoint(st,ed,idx)へ、変数名を変えた。

# Point_steds
Point(st,ed,s) PK(st,ed)

# Point\_xy
Point(x,y) PK(x,y)

# Point\_yx
Point(y,x) PK(y,x)
abc383/bより共通化
static classとしてMain内部に貼り付ける。
TreeSet<Point>とHashSet<Point>のどちらでも使える。

# Point\_yxidx
Point(y,x,idx) PK(y,x)

# Point_yxs
Point(y,x,s) PK(y,x)
abc386/dより共通化
static classとしてMain内部に貼り付ける。

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

# UnionFind\_map
abc277/dより共通化
static classとしてMain内部に貼り付ける。
int[] uf;の代わりにMap<Integer, Integer> map=new TreeMap<>();で管理する。

