# Main
n,mを読み込み、int[][] aryを用意し、jを0からm回ループし、iを0からn回ループし、ary[j][i]に読み込む。

int[][] matを用意し、jを0からm回ループし、iを0からn回ループし、
a=ary[j][i-1]、b=ary[j][i]を入れ、mat[a][b]++、mat[b][a]++をカウントする。

ans=0を用意し、jを1からnまでループし、iをj+1からnまでループし、mat[j][i]==0の場合ans++にカウントする。

ansを出力する。
