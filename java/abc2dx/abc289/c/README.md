# Main
n,mを読み込み、int[][] aryを用意する。
iを0からm回ループし、cを読み込み、jをc回ループし、
aを読み込み、ary[i][a-1]++にカウントする。

ans=0を用意し、iを0から2^m回ループし、int[] sumを用意し、jを0からm回ループし、
mask=1 << jを用意し、(i&mask)>0の場合、kを0からn回ループし、sum[k]+=ary[j][k]を加える。

bng=falseを用意し、kを0からn回ループし、sum[k]==0の場合、bng=trueを入れる。
!bngの場合、ans++をカウントする。

ansを出力する。
