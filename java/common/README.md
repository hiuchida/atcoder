# Counter
abc369/cより共通化
static classとしてMain内部に貼り付ける。
Map<Integer, Integer> mapを使って、int cの個数を数える。
String sの場合は、同名のクラスで別バージョンを作る。

# Point
abc383/bより共通化
static classとしてMain内部に貼り付ける。
TreeSet<Point>とHashSet<Point>のどちらでも使える。

# UnionFind
atc001/bより共通化
static classとしてMain内部に貼り付ける。
経路圧縮とサイズによる連結を実装したもの。

## UnionFind_ltrt
UnionFindに左端と右端を持たせる。
mergeは連続していることを前提に使う。
本来、low/highやfloor/ceilingかもしれない。
