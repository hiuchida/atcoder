# Main
int[] col,int[] row,int slashu,slashdに縦、横、／、＼をカウントする。
aを1baseから0baseにし、y=a/nとx=a%nの座標を計算する。
col[x]++とrow[y]++、x==yのときslashd++、x==n-y-1のときslashu++する。
それぞれ4つの集計値がnとなったら、iを出力する。
最後までビンゴしなければ-1を出力する。

x==n-y-1は、x+y==n-1の方がよいだろうか。
