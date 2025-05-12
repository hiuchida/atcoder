# Main
nを読み込み、int[] aryに読み込み、
long[] sumを用意し、sum[i+1]=sum[i]+ary[i]を入れる。

ans=0を用意し、iをn-1から1まで-1ループし、
x=ary[i]*sum[i]を入れ、ans+=xを加える。
ansを出力する。
