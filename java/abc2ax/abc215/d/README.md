# Main01
mまでの素数を列挙し、ary\[i\]で割り切れたらsetから省く。
WA8個。TLE4個。RE2個。

# Main02
mまでの素数を列挙し、ary\[i\]で割れるだけ1になるまで割る。setから省く。
WA9個。TLE11個。RE2個。

# Main03
mまでの素数を列挙し、素数のループの中でary\[i\]を割れるだけ割る。
WA9個。TLE9個。RE2個。

# Main11
pr=new Prime(M+1)を用意し、n,mを読み込み、int[] ary,apを用意する。
n回ループし、aを読み込み、pr.factors(a)の要素vをループし、ap[v]++に入れる。

List<Integer> listを用意し、listに1を追加する。
iを2からmまでループし、ap[i]>0の場合スキップする。
v=pr.minf[i]を入れ、ap[v]>0の場合スキップする。
listにiを追加する。

list.size()とlistを出力する。
WA7個。

# Main
v=pr.minf[i]　の代わりに、pr.factors(i)の要素vでループし、ap[v]>0の場合bok=falseにリセットし、
bokの場合に、listにiを追加する。

