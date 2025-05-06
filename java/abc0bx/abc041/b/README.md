# Main01
nを読み込み、rc = INT\_MAXを用意する。
iを1からn-1までループし、j=n/iを入れ、i>jの場合中断する。
d=j-i+n-i*jを入れ、rcをdの最小値に更新する。
rcを出力する。
WA1

# Main
Main01を元に、
n == 1の場合、rc=0を入れる。
AC 98ms

# Main2
Main01を元に、
iを1からn-1までループし、
を
iを1からnまでループし、
に修正。
AC 99ms

# Main3
r17を適用。
m = n - i * jを入れ、ansをabs(i - j) + mの最小値に更新する。
AC 78ms

# Main\_fix
書き直す。
x1=j-i、x2=n-i*jを入れ、ansをx1+x2の最小値に更新する。
AC 73ms

