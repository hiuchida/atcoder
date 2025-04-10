# Main
sを読み込み、int[][] aryを用意する。
iを0からn回ループし、sを読み込み、jを0からs.length回ループし、
idx=s.charAt(j)-'a'を入れ、ary[i][idx]++にカウントする。

StringBuilder sbを用意し、jを0から26回ループし、min=INT\_MAXを用意し、
iを0からn回ループし、minをary[i][j]の最小値で更新する。
iを0からmin回ループし、sbにj+'a'を追加する。

ループが終了したら、sbを出力する。
