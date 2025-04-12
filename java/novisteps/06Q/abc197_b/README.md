# Main
h,w,y,xを読み込み、Maze mzに読み込む。
ans=1を用意し、dを0からDY.length回ループし、iを1から無限ループし、
ny=y+i\*DY[d]、nx=x+i\*DX[d]を入れ、mz.map[ny][nx]<0の場合、中断する。
それ以外はans++にカウントする。

ansを出力する。
