# Main
nを読み込み、String[] aryに読み込む。
iを0からn-1までループし、jをi+1からn-1までループする。
ch1=ary[i].charAt(j)、ch2=ary[j].charAt(i)を入れる。
ch1,ch2がどちらも'D'、または'W'と'L'の組み合わせがスキップする。
それ以外はng。
ループが終了したらok。
