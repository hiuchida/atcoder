# Main01
n,mを読み込み、Counter cntを用意し、m回ループし、cntに(u,v)と(v,u)を追加する。
n!=m+1の場合ng。
iを1からnまでループし、v=cnt.get(i).size()を入れ、
v==0 || v>2の場合ng。
ループ終了したらok。
WA6個。

# Main
Main01を元に、
n!=m+1の場合ng。　を削除。
TreeSet<Integer> set、Deque<Bean> queを用意し、queにBean(1, 0)を追加する。
queが空になるまでループし、b=que.poll()を取出し、
setにb.vが存在する場合ng。
setにb.vを追加する。
cnt.get(b.v)の要素nxtをループし、nxt==b.idxの場合スキップする。
queにBean(nxt, b.v)を追加する。
ループ終了したら、set.size()!=nの場合ng。
それ以外はok。

# Main01\_fix
Main01と元に、

n!=m+1とv==0 || v>2のチェックでは、
以下の場合にokと誤判定される。
5 4
1 2
3 4
4 5
3 5

そこで、1-nまで連結していることをチェックするため、UnionFindで判定する。
UnionFind ufを用意し、uf.merge(u, v)する。
s=uf.size(i)を入れ、s!=nの場合ng。

