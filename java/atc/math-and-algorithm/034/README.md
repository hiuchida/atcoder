# Main
nを読み込み、int[] ax,ayに読み込む。
ans=INT\_MAXを用意し、iを0からn-1までループし、jをi+1からn-1までループし、
x=calc_dist(ax[i], ay[i], ax[j], ay[j])を入れ、ansにxの最小値を更新する。

ansを出力する。

calc_distの中で、距離を計算する。
AC 187ms
