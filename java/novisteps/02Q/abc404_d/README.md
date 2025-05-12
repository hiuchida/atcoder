# Main
n,mを読み込み、int[] acに読み込む。
int[][] aryを用意し、ary[a-1][i]=1をセットする。
int[] flagを用意し、dfs(0, 0, flag)を呼び出す。
ansを出力する。

dfsの中で、
i==nの場合、bok=trueを用意し、jを0からm回ループし、flag[j]<2の場合bok=falseを入れる。
bokの場合、ansにvの最小値を更新する。
戻る。

v0=v、flag0=Arrays.copyOf(flag, m)を用意し、dfs(i+1, v0, flag0)を呼び出す。
v1=v+ac[i]、flag1=add(flag, i)を用意し、dfs(i+1, v1, flag1)を呼び出す。
v2=v+2\*ac[i]、flag2=add2(flag, i)を用意し、dfs(i+1, v2, flag2)を呼び出す。
戻る。

addの中で、
ans=Arrays.copyOf(flag, m)を用意し、
jを0からm回ループし、ans[j]+=ary[i][j]を加える。
ansを返す。

add2の中で、
ans=Arrays.copyOf(flag, m)を用意し、
jを0からm回ループし、ans[j]+=2\*ary[i][j]を加える。
ansを返す。
AC 194ms

# Main\_2bit
2ビットで全探索する。

iを0から2^2\*n回ループし、bok=trueを用意し、
jを0からn回ループし、mask=3 << 2*jを用意し、
(i&mask)==maskの場合、bok=falseを入れる。
!bokの場合スキップする。

sum=0、int[] flagを用意し、
jを0からn回ループし、mask=3 << 2\*j、val1=1 << 2\*j、val2=2 << 2\*jを用意し、
(i&mask)==val1の場合、sum+=ac[j]を入れ、kを0からm回ループし、flag[k]+=ary[j][k]を入れる。
(i&mask)==val2の場合、sum+=2L\*ac[j]を入れ、kを0からm回ループし、flag[k]+=2\*ary[j][k]を入れる。

bok=trueを用意し、jを0からm回ループし、flag[j]<2の場合bok=falseを入れる。
!bokの場合、sum=LONG\_MAXを入れる。
ansをsumの最小値に更新する。
AC 221ms

