# Main01
n,mを読み込み、int[] aryに読み込み、ソートする。
UnionFind ufを用意し、iを1からn-1までループし、
ary[i-1]+1==ary[i]の場合、uf.merge(ary[i-1], ary[i])する。
ループ終了したら、ary[0]==0 && ary[n-1]==m-1の場合、uf.merge(ary[0], ary[n-1])する。
Counter cntを用意し、iを0からn回ループし、
r=uf.root(ary[i])を得て、cntに(r, ary[i])を追加する。
List<Long> listを用意し、cntの要素kをループし、
v=cnt.get(k)を得て、listにv.vを追加する。
ループ終了したらlistをソートし、末尾を取り除く。
ans=0を用意し、listの要素vをループし、ans+=vに加える。
ループ終了したらansを出力する。
RE26個。

# Main02
Main01を元に、
Beanのint vをlong vに変更し、
listにv.vを追加する際に(long)が不要。
RE26個。

# Main03
Main02を元に、
for (long v : list) {　の外側に　if (list.size()>0) {　を追加。
RE26個。

# Main04
Main03を元に、REの原因は10^9のnew int\[n\]のため、
UnionFindの　int[] uf　を　Map<Integer, Integer> map=new TreeMap<>()　に変更する。
same()とsize()は未使用のためコメントアウト。
WA8個。

# Main
Main04を元に、UnionFindを順に呼んでいるので問題ないと想定していたが、
root()の中でroot()を再帰呼び出しして、祖先を探し、経路更新する。
merge()の中でroot()を呼び出して、祖先を比較する。

# Main\_fix
Mainを元に、UnionFindのsame()とsize()が動作するように修正。
mapに親は自分自身の正の添字を持たせていたが、オリジナルと同様に負のサイズを持たせる。
初期状態はmapは空だが、root()で問い合わせると、自分自身のみのサイズ-1をmapに追加する。
merge(),same(),size()がroot()を呼び出しているので、それぞれのタイミングでmapが更新される。

