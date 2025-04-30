# Main
nを読み込み、int[] aryに読み込み、SegmentTree stを用意する。
n回ループし、st.update(i, ary[i])に更新する。
iを0からn回ループし、
v=st.findRight(0, i, ary[i])を入れ、
v>=0の場合v++を加える。
vを出力する。
