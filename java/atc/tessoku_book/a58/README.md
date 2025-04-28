# Main
SegmentTree_max20250427でのテスト。

n,qを読み込み、SegmentTree stを用意する。
q回ループし、cmdを読み込み、
cmd==1の場合、st.update(pos-1, x)を更新する。
cmd==2の場合、st.query(l-1, r-1)を出力する。
