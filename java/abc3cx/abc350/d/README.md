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

