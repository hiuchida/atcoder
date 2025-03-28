# Main01
nを読み込み、long[][] aryのary\[i\]\[j\]とary\[j\]\[i\]に読み込む。
boolean[] flagを用意し、dfs(0, flag, 0)を呼び出し、ansを出力する。

dfs内で、idx==2*nとなったらansをvalの最大値で更新する。
iを1から2\*n-1までループし、flag\[i\]がfalseならば、続行する。
jをi+1から2\*nまでループし、flag\[j\]がfalseならば、val2 = val ^ ary[i][j]を計算し、dfsを再帰呼び出しする。
TLE15個。

# Main02
Main01を元に、
idx==0 && i>1
idx==2 && i>3
idx==4 && i>5
idx==6 && i>7
idx==8 && i>9
idx==10 && i>11
idx==12 && i>13
idx==14 && i>15
のときに内側のループを除外する。
TLE13個。

# Main
Main01を元に、
dfsのiを初めてflag\[i\]がfalseとなる左端のみを採用する。

