# Main01
事前にlong[] factに2\*N個のn!を計算する。

a,b,c,dを読み込み、
ans=1、ab=a+b、cd=c+dを用意し、
fab=fact[ab]、fa=fact[a]、fb=fact[b]を用意し、
fcd=fact[cd]、fc=fact[c]、fd=fact[d]を用意し、
x=modmul(fab, modinv(fa, M))、x=modmul(x, modinv(fb, M))を入れ、
y=modmul(fcd, modinv(fc, M))、y=modmul(y, modinv(fd, M))を入れ、
z=modmul(x, y)、ans=modadd(ans, z)を入れ、
ansを出力する。
WA16個。

# Main02
Main01を元に、

事前にlong[] factに3\*N個のn!を計算する。

ans=0、bcd=b+c+d、fbcd=fact[bcd]を用意し、
x=fbcd/fb/fc/fd　に変更。

サンプルでWAなので未提出。
