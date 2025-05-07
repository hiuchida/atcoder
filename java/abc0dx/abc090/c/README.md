# Main01
n,mを読み込み、ans=0を用意する。
n>=3 && m>=3の場合、ans=(n-2)*(m-2)
n==1の場合、ans=m-2
m==1の場合、ans=n-2
n==2 || m==2の場合、ans=0
ansを出力する。

3x3の場合
4 6 4
6 9 6
4 6 4

2x3の場合
4 6 4
4 6 4

1x3の場合
2 3 2

WA4個。

# Main02
Main01を元に、
int ansをlong ansに修正。
ans=(n-2)\*(m-2)をans=(long)(n-2)\*(m-2)に修正。
WA1個。

# Main
Main02を元に、
n==1 && m==1の場合、ans=1

1x1の場合
1

AC 73ms

