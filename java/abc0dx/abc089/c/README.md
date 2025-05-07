# Main
nを読み込み、int[] idxを用意する。
n回ループし、lineを読み込み、ch=line.charAt(0)を入れ、
ch=='M'の場合idx\[0\]++
ch=='A'の場合idx\[1\]++
ch=='R'の場合idx\[2\]++
ch=='C'の場合idx\[3\]++
ch=='H'の場合idx\[4\]++

ans = 0を用意し、
iを0から3回ループし、jをi+1からj<4までループし、kをj+1からk<5までループし、
idx[i] > 0 && idx[j] > 0 && idx[k] > 0の場合、ans += (long) idx[i] * idx[j] * idx[k]を加える。

ansを出力する。
AC 131ms

# Main\_fix
書き直す。
int[] aryにカウントし、boolean[] flagを用意し、
dfs(0, 0, 1, flag)を呼び出し、ansを出力する。

dfsの中で、
i==3の場合、ans+=vを加えて、戻る。
jをstからN-1までループし、flag[j]やary[j]==0の場合スキップする。
flag[j]=trueをセットし、dfs(i+1, j+1, v*ary[j], flag)を呼び出し、flag[j]=falseをリセットする。
AC 285ms

