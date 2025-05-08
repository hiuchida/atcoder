# Main01
n,qを読み込み、int[] aryに読み込む。
long[] dif、sum=0を用意し、iを0からn回ループし、
dif[i]=ary[i+1]-ary[i]を入れ、
i>0の場合sum+=abs(dif[i])を加える。

q回ループし、l,r,vを読み込み、
iをlからrまでループし、ary[i]+=vを加える。
pl=dif[l-1]、pr=dif[r]を入れ、
dif[l-1]+=v、dif[r]-=vを加え、
l>1の場合sum+=abs(dif[l-1])-abs(pl)を入れ、
r<nの場合sum+=abs(dif[r])-abs(pr)を入れ、
sumを出力する。
TLE3個。

# Main
Main01を元に、デバッグ目的のary更新を削除する。

iをlからrまでループし、ary[i]+=vを加える。
を削除。
AC 932ms

