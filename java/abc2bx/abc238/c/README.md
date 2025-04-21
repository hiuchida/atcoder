# Main01
nを読み込み、ans=0を用意し、ans=calc(ans, n)を呼び出し、ansを出力する。

calcの中で、
n<10の場合、calc1(ans, n)を呼び出し、そのまま返す。
n<100の場合、calc2(ans, n)を呼び出し、そのまま返す。
n<1000の場合、calc3(ans, n)を呼び出し、そのまま返す。

calc1の中で、
ans+n*(n+1)/2を返す。

calc2の中で、
ans+=calc1(ans, 9)を呼び出し、
n-=10-1を入れ、ans+n*(n+1)/2を返す。

calc3の中で、
ans+=calc2(ans, 99)を呼び出し、
n-=100-1を入れ、ans+n*(n+1)/2を返す。

途中で力技で書いても、n\*(n+1)/2がオーバーフローすることに気が付き、諦めた。
n\*(n+1)%Mはできるが、2で割れない。

WA22個。

# Main
Main01を元に、
calc2,calc3を一般化し、calc(long n, long w)にする。

2で割るのは、M_2=(M+1)/2倍すると、2\\*M\_2=M+1のため1となる。

calc(long n)の中で、
w=10を用意し、無限ループし、n<wの場合calc(n, w)を呼び出し、そのまま返す。
w*=10を更新する。

calc(long n, long w)の中で、
ans=0を用意し、w/=10を更新し、
w>1の場合、ans=calc(w-1, w)を呼び出す。

n-=w-1を更新し、nn=modmul(n, n+1)、nn2=modmul(nn, M_2)を入れ、modadd(ans, nn2)を返す。

