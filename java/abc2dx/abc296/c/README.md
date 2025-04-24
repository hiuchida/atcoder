# Main
n,xを読み込み、int[] aryに読み込み、ソートする。
int[] difを用意し、iを1からn-1までループし、dif[i]=ary[i]-ary[i-1]を入れる。
long[] sumを用意し、iを1からn-1までループし、sum[i]=sum[i-1]+dif[i]を入れる。
x<0の場合、x=-xに更新する。
iを0からn回ループし、v=sum[i]+x、idx=Arrays.binarySearch(sum, v)を呼び出し、
idx>=0の場合ok。
ループ終了したらng。

# Main\_fix
int\[\] difやlong\[\] sumを計算したが、ary\[i\]+xを探せばよいので、削除する。

# Main_syakutori
最初、尺取りで探そうとして断念したので、作り直す。

