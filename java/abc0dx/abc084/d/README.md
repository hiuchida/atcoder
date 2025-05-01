# Main
PrimeMini pr,int[] ary,sumを用意し、
iを2からMまでループし、j=(i+1)/2を入れ、
pr.isp[i] && pr.isp[j]の場合、ary[i]=1を入れる。
sum[i]=sum[i-1]+ary[i]を入れる。

qを読み込み、q回ループし、
l,rを読み込み、sum[r]-sum[l-1]を出力する。
