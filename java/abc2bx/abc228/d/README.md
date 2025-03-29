# Main01
long[] aryを用意し、すべての要素を-1で初期化する。
qを読み込み、iを0からq-1までループする。
t,xを読み込み、h=x%Nを計算する。
t==1の場合、ary[h]>=0の間ループしh=(h+1)%Nを計算する。
ループが終了したらary[h]にxを入れる。
t==2の場合、ary\[h\]を出力する。
TLE2個。

# Main
long[] aryを用意し、すべての要素を-1で初期化する。
int[] rt付きのUnionFind ufを初期化する。
qを読み込み、iを0からq-1までループする。
t,xを読み込み、h=x%Nを計算する。
t==1の場合、ary[h]<0の場合set(h, x)を呼び出す。
それ以外の場合、rt=uf.right(h)で右端を得て、+1する。
rt==0の場合、末尾から先頭に移動したため、ary[rt]<0の場合set(rt, x)を呼び出す。
それ以外の場合、rt2=uf.right(rt)で右端を得て、+1し、set(rt2, x)を呼び出す。
rt!=0の場合、set(rt, x)を呼び出す。
t==2の場合、ary\[h\]を出力する。

setの中で、ary[h]>=0は内部エラーを発生させる。
ary[h]にxを入れる。
h>0で左隣h-1が存在する場合、mergeする。
h<N-1で右隣h+1が存在する場合、mergeする。

int[] rt付きのUnionFind ufが誤判定させないため、ary\[N-1\]とary\[0\]はmergeしない。

# Main\_fix
UnionFindを使わずに、右端の-1を探すint\[\] rightを用意する。
right\[i\]=iで初期化する。

set内で、searchを呼び出して、hに対する右端の-1の位置を探す。
ary[hh]にxを入れる。
h2に右隣の位置を計算する。
right[hh]にright[h2]を入れる。（更新時には経路圧縮しない）

search内で、right[h]==hのときに、再帰呼び出しを打ち切る。
right[h]をsearchを呼び出し、right[h]にxを更新し経路圧縮し、xを返す。

Mainと比較して、ary\[N-1\]とary\[0\]も繋がっているため、t==1の処理はset(h, x);するだけ。

