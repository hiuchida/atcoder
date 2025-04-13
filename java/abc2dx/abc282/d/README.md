# Main01
n,mを読み込み、Counter cnt、UnionFind ufを用意する。
m回ループし、u,vを読み込み、cntに(u,v)と(v,u)を追加し、uf.merge(u,v)を追加する。
int[] aryを用意し、-1で初期化する。
iを1からnまでループし、ary[i]<0の場合、
Deque<Bean> queを用意し、queにBean(i, 0)を追加し、ary[i]=0を更新する。
queが空になるまでループし、cur=que.poll()を取出し、nxtidx=(cur.idx+1)を入れる。
cnt.get(cur.v)の要素nxtでループし、
ary[nxt]<0の場合、queにBean(nxt, nxtidx)を追加し、ary[nxt]=nxtidxを更新する。

ここで断念。
WA50個。

# Main

