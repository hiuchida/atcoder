# Main
n,xを読み込み、int[] aa,abに読み込む。
int[][] aryを用意し、ary[0][0]=1に初期化する。
iを0からn-1までループし、
jを0からx-1までループし、
ary[i][j]>iの場合、ja=j+aa[i]とx+1の最小値、jb=j+ab[i]とx+1の最小値とし、
ary[i+1][ja]とary[i+1][jb]にary[i][j]+1を入れる。
ループが終わったとき、ary[n][x]>0ならばok、それ以外はng。
