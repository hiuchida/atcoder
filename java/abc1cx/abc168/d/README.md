# Main01
n,mを読み込み、Counter cntを用意し、a,bを読み込み、cntに(a,b)と(b,a)を追加する。
Deque<Bean> que、int[] flagを用意し、flag\[i\]=MAX\_INTで初期化する。
queにBean(1, 0)を追加する。
queが空になるまでループし、b=que.poll()に取出し、flag[b.v]<b.idxの場合、スキップする。
flag[b.v]=b.idx+1を更新し、cnt.get(b.v)の要素nxtでループし、
flag[nxt]>b.idx+1の場合、queにBean(nxt, b.idx+1)を追加する。

iを2からnまでループし、flag[i]==MAX\_INTの場合、ng。
iを2からnまでループし、v=flag[i]に入れ、cnt.get(b.v)の要素nxtでループし、
flag[nxt]+1==vの場合、nxtを出力する。
WA19個。TLE4個。

# Main02
Main01を元に、
flag[b.v]=b.idx+1;
を
flag[b.v]=b.idx;
に修正。
TLE2個。

# Main03
Main02を元に、
Beanにint pを追加。（int v、int idxをint c、int sに変更）
int[] prevを追加。
queにBean(1, 0, 0)を追加する。
prev[b.c]=b.p;を更新する。
queにBean(nxt, b.c, b.s+1)を追加する。
iを2からnまでループし、prev[i]を出力する。

# Main
Main03を元に、
if (flag[b.c]<b.s) continue;
を
if (flag[b.c]<=b.s) continue;
に修正

if (flag[nxt]>=b.s+1) {
を
if (flag[nxt]>b.s+1) {
に修正

