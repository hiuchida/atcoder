# Main
UnionFind20250102でのテスト。

n,qを読み込み、UnionFind ufを用意する。
q回ループし、cmd,u,vを読み込み、
cmd==1の場合、uf.merge(u, v)を更新する。
cmd==2の場合、uf.same(u, v)がtrueならば1、それ以外は0を出力する。
