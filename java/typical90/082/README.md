# Main01
l,rを読み込み、ar=calc(r)、al=calc(l-1)を呼び出し、
modadd(ar, -al)を出力する。

calc(long x)の中で、
ans=0を用意し、
x==0の場合、0を返す。
x==(long)1e18の場合、ans+=19、x--を入れる。
m=10を用意し、iを1から18までループし、
m/10<=x && x<mの場合、ans=modadd(ans, calc(m/10, x, i))を呼び出し、中断する。
ans=modadd(ans, calc(m/10, m-1, i))を呼び出し、m*=10を入れる。
ansを返す。

calc(long x, long y, long z)の中で、
ans=modmul(z, x+y)、ans=modmul(ans, y-x+1)、ans=modmul(ans, M_2)を入れ、
ansを返す。
WA3個。

# Main
x==10^18の場合、ans+=19を加えていたが、実際は19文字が10^18個なので、
N=(long)1e18、ans+=modmul(19, N)に修正する。
AC 74ms

