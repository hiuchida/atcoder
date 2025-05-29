# Main
n,xを読み込み、int[] aに読み込む。
long[] s、sum = 0を用意し、
iを0からn-2までループし、
s[i] = a[i] + a[i+1]を入れ、
s[i] > xの場合、m = s[i] - xを入れ、
m <= a[i+1]の場合、a[i+1] -= m、sum += mを入れ、
それ以外の場合、a[i+1] = 0、sum += mを入れる。
sumを出力する。
AC 237ms

# Main\_fix
書き直す。

n,xを読み込み、int[] aryに読み込む。
int[] sumを用意し、iを0からn回ループし、
sum[i]=ary[i]を入れ、i+1<nの場合、sum[i]+=ary[i+1]を加える。
ans=0を用意し、iを0からn回ループし、d=sum[i]-xを入れ、
d>0の場合、ans+=d、sum[i]-=dを入れ、
i+1<nの場合、d2=Math.min(sum[i+1], d)、sum[i+1]-=d2を入れる。
ansを出力する。
AC 346ms

