# Main01
Counter cにa,bのカウントする。
UnionFind ufにmerge(a,b)する。
iを1からnまでループし、uf.root(i)ごとに、
c.get(i)のカウントを合計する。
sC2の組み合わせ数all=s*(s-1)/2を計算し、合計したカウントを引く。
WA11個。

# Main
Counter cにa,bのカウントする。
UnionFind ufにmerge(a,b)する。
iを1からnまでループし、uf.root(i)ごとに、
c.get(i)のカウントを合計する。
sC2の組み合わせ数all=(long)s*(s-1)/2を計算し、合計したカウントを引く。

s\*(s-1)のときにオーバーフローしていた。

# Main\_fix
Counter_int_int20250410を適用
static long calc(int a, int b) { //abc295_d,abc350\_d,abc355_d,typical90_084: aCbの組み合わせ数

