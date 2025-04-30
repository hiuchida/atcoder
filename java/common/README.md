# Bean\_int
abc168/dより共通化
static classとしてMain内部に貼り付ける。
MapのValueに入れることを前提にする。
Map<Integer, Integer>ではValueを更新する度にラッパークラスを作り直すが、
Map<Integer, Bean>ならばBeanの中を更新できる。

# Bean\_long
abc278/dより共通化
static classとしてMain内部に貼り付ける。
MapのValueに入れることを前提にする。
Map<Integer, Integer>ではValueを更新する度にラッパークラスを作り直すが、
Map<Integer, Bean>ならばBeanの中を更新できる。

# Counter\_int\_int
abc082/cより共通化
static classとしてMain内部に貼り付ける。
Map<Integer, Integer> mapを使って、int kの個数を数える。
get(),put()を提供し、remove()は暗黙的に実行される。
内部値の更新時、カウントが0になったときにremove()する。
inc(),dec(),add(),sub()で内部値を更新する。
## Counter_int_int20250410
abc082/c,abc183/f,abc231/d,abc252/d,abc273/c,abc350/d,abc369/c,abc398/c
abc210/c,abc241/b,abc360/c,abc397/c: dec()使用
## Counter_int_int20250413
abc253/c: NavigableSet<Integer> keySet()使用
## Counter_int_int20250416
abc136/d,abc292/d,abc295/d: public int hashCode()、public boolean equals(Object obj)を追加

# Counter_int_listbean
abc362/dより共通化
abc362/d
static classとしてMain内部に貼り付ける。
Map<Integer, List<Bean>> mapを使って、int kに関連したBean xをリストに追加する。
get(),put(),remove()を提供する。
内部リストは、暗黙的なremove()はしない。
add()で内部リストに追加する。
他のリスト操作はget()で取得して行うが、反映させるためにはput()を呼ぶ必要がある。
ただし新規はput()を呼ばないと反映されないが、既存の場合は参照渡しなので、put()を呼ばずに捨てても変更は反映される。
get()で取得したList<Bean>からすべて削除しても、0件でmapに残る。

# Counter\_int\_listint
abc248/dより共通化
static classとしてMain内部に貼り付ける。
Map<Integer, List<Integer>> mapを使って、int kに関連したint idxをリストに追加する。
get(),put(),remove()を提供する。
内部リストは、暗黙的なremove()はしない。
add()で内部リストに追加する。
他のリスト操作はget()で取得して行うが、反映させるためにはput()を呼ぶ必要がある。
ただし新規はput()を呼ばないと反映されないが、既存の場合は参照渡しなので、put()を呼ばずに捨てても変更は反映される。
get()で取得したList<Integer>からすべて削除しても、0件でmapに残る。
## Counter\_int\_listint20250410
abc168/d,abc223/d,abc248/d,abc270/c,abc276/b,abc277/c,abc282/c,abc288/c,abc399/c
## Counter\_int\_listint20250413
abc211\/d,abc260/d,abc287/c,abc292/d: NavigableSet<Integer> keySet()使用

# Counter_int_setint
abc278/cより共通化
static classとしてMain内部に貼り付ける。
Map<Integer, TreeSet<Integer>> mapを使って、int kに関連したint idxをセットで管理する。
get(),put(),remove()を提供する。
内部セットは、暗黙的なremove()はしない。
add(),del(),is()で内部セットを操作する。
他のセット操作はget()で取得して行うが、反映させるためにはput()を呼ぶ必要がある。
ただし新規はput()を呼ばないと反映されないが、既存の場合は参照渡しなので、put()を呼ばずに捨てても変更は反映される。
del()ですべて削除しても、0件でmapに残る。
## Counter_int_setint250414
abc223/d: WAソース。Counter_int_listint20250410に変更
abc273/d: get().lower(),get().higher()使用
abc278/c: del(),is()使用

# Counter_long_int
abc233/dより共通化
abc233/d
static classとしてMain内部に貼り付ける。
Map<Long, Integer> mapを使って、long kの個数を数える。
get(),put()を提供し、remove()は暗黙的に実行される。
内部値の更新時、カウントが0になったときにremove()する。
inc(),dec(),add(),sub()で内部値を更新する。

# Counter_str_int
abc231/bより共通化
abc008/b,abc091/b,abc231/b,abc261/c
static classとしてMain内部に貼り付ける。
Map<String, Integer> mapを使って、String kの個数を数える。
get(),put()を提供し、remove()は暗黙的に実行される。
内部値の更新時、カウントが0になったときにremove()する。
inc(),dec(),add(),sub()で内部値を更新する。

# Counter_str_liststr
abc285/dで作成したが、未使用。
Map<String, List<String>> mapを使って、int kに関連したString valをリストに追加する。

# Counter_str_setstr
未使用。
Map<String, TreeSet<String>> mapを使って、int kに関連したString valをセットで管理する。

# FenwickTree_int
practice2/bで作成したが、未使用。
## FenwickTree_int20250427
practice2/bで作成したが、未使用。

# FenwickTree_long
practice2/b
## FenwickTree_long20250427
practice2/b

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

# ModFund
staticMethodsより共通化
staticメソッドとしてMain内部に貼り付ける。
今のところ状態を保持しないため、クラスにはしない。
## ModFunc20250421
abc052_c,abc055\_b,abc065_c,abc211_c,abc211_d,abc242\_c,abc248\_c,abc291_d: long mod(long val)
abc211_c,abc211_d,abc242\_c,abc248\_c,abc291_d,abc401\_c: long modadd(long val, long x)
abc052_c,abc055\_b,abc065_c: long modmul(long val, long x)
## ModFunc20250422
abc275\_b,abc353\_d,abc401\_c: long mod(long val) val<0対応
## ModFunc20250423
abc275\_b,abc353\_d: long modadd(long val, long x) mod(val)\+mod(x)
abc275\_b,abc353\_d: long modmul(long val, long x) mod(val)\*mod(x)
## ModFunc20250424
abc238\_c,abc298\_d: long modinv(long val, long m)

# MyArray\_int
abc280/dより共通化
static classとしてMain内部に貼り付ける。
ArrayList<Integer>のラッパークラスのオーバーヘッド削減のため、
add()の際に自動拡張するint\[\]を管理する。
## MyArray\_int20250420
abc280/d: Prime内で使用したが、最終的に未使用
## MyArray\_int20250423
abc301/b: String join(String delimiter)
## MyArray\_int20250424
abc296/d: String toString()
## MyArray\_int20250425
Prime20250426: size()、get()を追加。

# MyDeque\_int
typical90/061より共通化
static classとしてMain内部に貼り付ける。
ArrayDeque<Integer>で十分使えるが、get(int idx)がないため、
バッファサイズが固定で、addFirst,addLast,getのみ提供する。
## MyDeque\_int20250105
typical90/061
## MyDeque\_int20250326
abc237/d: size(),removeFirst()
## MyDeque\_int20250327
abc298/d: removeLast()
## MyDeque\_int20250425
addFirst()、addLast()の中で、grow()により自動拡張する。

# MyDeque\_long
abc389\/cより共通化
## MyDeque\_long20250316
abc389\/c
## MyDeque\_long20250327
未使用
size(),removeLast()
## MyDeque\_long20250425
未使用
addFirst()、addLast()の中で、grow()により自動拡張する。

# MyHeapque_int
abc217/eより共通化
static classとしてMain内部に貼り付ける。
PriorityQueue<Integer>で十分使えるが、int\[\]で管理した方が低コスト。
MyArrayと同様に、add()の際に自動拡張する。
int型のためnullが表現できないため、削除フラグとしてInteger.MAX_VALUEを使用している。
このためInteger.MAX_VALUEをデータ値として管理することはできない。
デバッグ出力の際にInteger.MAX_VALUEを削減するため、末尾の2つの葉がInteger.MAX_VALUEとなった場合のみ
0でリセットし、cnt-=2する。
## MyHeapque_int20250428
abc217/e

# MyHeapque_long
abc212/dより共通化
## MyHeapque_long20250428
abc212/d

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
## Prime20250103
abc149\_c,abc215\_d,abc239\_d,abc250\_d,abc284\_d,abc383\_d
## Prime20250330
abc149\_c,abc292\_c
boolean check(int x)を追加
## Prime20250418
abc292_c
int[] minf;、long count(int n)を追加
## Prime20250419
abc254\_d
boolean check(long x)に変更
## Prime20250420
abc052\_c,abc149\_c,abc254\_d
int[] divisors(int n)を追加
## Prime20250421
abc052\_c,abc172\_d,abc215\_d,abc280\_d
long sum(int n)、int[] factors(int n)、int[][] factorize(int n)を追加
## Prime20250425
MyArrayを使用し、List<Integer> listをint\[\] lstに変更。
divisors()、factors()、factorize()の中でMyArrayを使用。

# PrimeMini
Primeより共通化
static classとしてMain内部に貼り付ける。
int[] minfを削除し、機能制限した。
count()、sum()、divisors()、factors()、factorize()を削除。
## PrimeMini20250421
Prime20250421から分離。
## PrimeMini20250425
abc300\_d
MyArrayを使用し、List<Integer> listをint\[\] lstに変更。

# Que_cur
abc287/cより共通化
static classとしてMain内部に貼り付ける。
Deque<Que>に使うことを前提にする。

# Que_curpre
abc168/dより共通化
static classとしてMain内部に貼り付ける。
Deque<Que>に使うことを前提にする。

# Que_curprestp
abc168/dより共通化
static classとしてMain内部に貼り付ける。
Deque<Que>に使うことを前提にする。

# SegmentTree\_ave
未使用
Bean(cnt,sum)を集計する。
平均値はsum/cntを計算する。
## SegmentTree\_ave20250429
未使用

# SegmentTree\_max
tessoku_book/a58
practice2/j
最大値を集計する。
## SegmentTree\_max20250427
tessoku_book/a58
## SegmentTree\_max20250428
未使用
int max()を独立。
## SegmentTree\_max20250429
practice2/j
findLeft(),findRight()追加

# SegmentTree\_min
未使用
最小値を集計する。
## SegmentTree\_min20250428
未使用
## SegmentTree\_min20250429
abc217/e
findLeft(),findRight()追加

# SegmentTree\_sum
tessoku_book/a59
合計値を集計する。
## SegmentTree\_sum20250427
tessoku_book/a59

# SegmentTree\_xor
abc185/f
xor値を集計する。
## SegmentTree\_xor20250427
abc185/f

# staticFields
static変数としてMain内部に貼り付ける。
原則、staticメソッドを必要とするものはクラス化する。

# staticMethods
staticメソッドとしてMain内部に貼り付ける。
原則、static変数を必要とするものはクラス化する。

# Twin\_int
abc289/bより共通化
static classとしてMain内部に貼り付ける。
MapのValueに入れることを前提にする。

# UnionFind
atc001/bより共通化
static classとしてMain内部に貼り付ける。
経路圧縮とサイズによる連結を実装したもの。
## UnionFind20250102
atc001/b
practice2/a

# UnionFind\_2d
abc269/dより共通化
static classとしてMain内部に貼り付ける。
(x,y)をx+y\*wの添字に変換して管理する。
## UnionFind_2d20250416
abc269/d
abc351/d
typical90/012

# UnionFind_ltrt
abc370/dより共通化
UnionFindに左端と右端を持たせる。
mergeは連続していることを前提に使う。
本来、low/highやfloor/ceilingかもしれない。
## UnionFind_ltrt20250416
abc228/d
abc370/d

# UnionFind\_map
abc277/dより共通化
static classとしてMain内部に貼り付ける。
int[] uf;の代わりにMap<Integer, Integer> map=new TreeMap<>();で管理する。
## UnionFind\_map20250416
abc277/d

