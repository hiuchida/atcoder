# Main
nを読み込み、int[] aryに読み込み、s=0を用意し、s+=ary[i]を加える。
s%10!=0の場合ng。
long[] sumを用意し、iを0からn回ループし、sum[i+1]=sum[i]+ary[i]を入れる。
iを0からn回ループし、sum[n+i+1]=sum[n+i]+ary[i]を入れる。
iを0から2\*n回ループし、
x=sum[i]、y=s/10、z=x+yを入れ、idx=binarySearch(sum, z)を呼び出す。
idx>=0の場合ok。
ループ終了したらng。
AC 357ms
