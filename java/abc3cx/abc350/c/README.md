# Main01
軽い気持ちで、2重ループでソートして、TLE18個。

# Main02
int[] idxにidx[ary[i]]=i+1を入れて、値の添字をメモする。
iを1からnまでループし、idx\[i\]とiが異なれば交換する。
値は、ary[idx[i]-1]とary[i-1]をswapする。
添字は、idx[ary[i-1]]とidx[i]をswapする。
WA21個。

# Main
int[] idxにidx[ary[i]]=i+1を入れて、値の添字をメモする。
iを1からnまでループし、idx\[i\]とiが異なれば交換する。
値は、ary[idx[i]-1]とary[i-1]をswapする。
添字は、idx[ary[i-1]]とidx[i]をswapする。

SampleもWAしていて、出力形式の2つの値はi<jに揃えてAC。

# Main\_fix
int[] ary=new int[n+1];にして、1baseでary\[i\]にアクセスできるようにする。
i1を1からnまでループ、その値はv1=ary[i1]。
v2=i1としたとき、その添字はi2=idx[v2]。

