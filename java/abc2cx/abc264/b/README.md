# Main
n=15とし、r,cを読み込み、int[][] aryを用意する。
init(1, n)、init(3, n-2)、init(5, n-4)、init(7, n-6)で黒い四角形をセットする。
ary[r][c]==1ならば黒、それ以外は白。

init内で、iをltからrtまでループし、
ary[i][lt]、ary[i][rt]、ary[lt][i]、ary[rt][i]を同時に1にセットする。
i==ltやi==rtのときに重なるが、結果は同じなので気にしない。
