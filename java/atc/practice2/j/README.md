# Main
SegmentTree_max20250429でのテスト。

tessoku_book/a58と似ているが、cmd==3が追加されているのと、
cmd==2が何気にArを含む、含まないが異なる。

n,qを読み込み、SegmentTree stを用意する。
n回ループし、st.update(i, a)を更新する。

q回ループし、cmdを読み込み、
cmd==1の場合、st.update(pos-1, x)を更新する。
cmd==2の場合、st.query(l-1, r)を出力する。
cmd==3の場合、st.findLeft(pos-1, n, x)+1を出力する。
