# Main01
n,mを読み込み、ans = 0を用意する。
n == 1 && m == 1の場合、ans = 1を入れる。
ansを出力する。
WA9個。

# Main02
Main01を元に、
n % 2 == 0 || m % 2 == 0の場合、何もしない。
WA9個。

# Main03
Main01を元に、
n == 1 && m == 1の場合、ans = 1を入れる。
n == 1の場合、ans = max(0, m - 2)を入れる。
m == 1の場合、ans = max(0, n - 2)を入れる。
それ以外は、ans = max(0, n - 2) * max(0, m - 2)を入れる。
WA3個。

# Main04
Main03を元に、
n == 1の場合、ans = max(0, m - 2)を入れる。
を
n == 1の場合、ans = m - 2を入れる。
に修正。

m == 1の場合、ans = max(0, n - 2)を入れる。
を削除。

それ以外は、ans = max(0, n - 2) * max(0, m - 2)を入れる。
を
それ以外は、ans = (n - 2) * (m - 2)を入れる。
に修正。
WA4個。

# Main
Main04を元に、
m == 1の場合、ans = n - 2を入れる。
に追加。

それ以外は、ans = (n - 2) * (m - 2)を入れる。
を
それ以外は、ans = (long)(n - 2) * (m - 2)
に修正。
AC 71ms

# Main\_fix01
書き直す。

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

# Main\_fix02
Main\_fix01を元に、
int ansをlong ansに修正。
ans=(n-2)\*(m-2)をans=(long)(n-2)\*(m-2)に修正。
WA1個。

# Main\_fix
Main\_fix02を元に、
n==1 && m==1の場合、ans=1

1x1の場合
1

AC 73ms

