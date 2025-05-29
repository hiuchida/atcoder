# Main
O(n^2)の回答する。
nを読み込み、boolean[] flagを用意し、flag[2]=trueを初期化する。
iを3からnまでループし、bng=falseを用意する。
jを2からi-1までループし、!flag[j]の場合スキップする。
i%j==0の場合、bng=trueを入れ、中断する。
!bngの場合、flag[i]=trueを入れる。

StringBuilder sbを用意し、
iを2からnまでループし、flag[i]の場合sb.append(i).append(" ")を追加する。

sb.toString().trim()を出力する。
