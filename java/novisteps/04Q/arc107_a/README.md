# Main01
a,b,cを読み込み、ans = 0を用意する。
iを1からaまでループし、
jを1からbまでループし、
kを1からcまでループし、
x = (long) (i * j * k) % m、ans = (ans + x) % mを入れる。
ansを出力する。
TLE5個。

# Main
xa = (long) a * (a + 1) / 2 % m
xb = (long) b * (b + 1) / 2 % m
xc = (long) c * (c + 1) / 2 % m
ans = ((xa * xb) % m * xc) % mを入れ、
ansを出力する。
AC 153ms

# Main\_fix
書き直す。

aa=calc(1, a)、bb=calc(1, b)、cc=calc(1, c)を入れ、
ans=modmul(aa, bb)、ans=modmul(ans, cc)を入れる。
ansを出力する。
AC 70ms
