# Main
h,wを読み込み、n=min(h, w)を入れ、int[] ansを用意する。
Maze mzに読み込む。
y,xを1からh,wまでループし、mz.map[y][x]<0の場合、find(y, x)を呼び出す。
iを0からn回ループし、ans[i]を出力する。

findの中で、dを0から無限ループし、mz.map[y+d][x+d]>=0の場合中断する。
mz.map[y+d][x+d]=d+1を更新する。

x+=d-1を入れ、iを0からd回ループし、mz.map[y+i][x-i]=i+1を更新する。

d/=2を計算し、ans[d-1]++をカウントする。
