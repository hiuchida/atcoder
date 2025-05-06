# Main01
s,kを読み込み、Set<String> setを用意する。
iを1からs.length()までループし、
jを0からs.length() - iまでループし、
sub = s.substring(j, j + i)を入れ、set.add(sub)に追加する。
List<String> slにsetを入れ、slをソートする。
sl.get(k - 1)を出力する。
TLE8個。部分点

# Main02
jを0からs.length()回ループし、
iを1からs.length() - jまでループし、set.contains(sub)の場合スキップする。
sl.size() < kの場合、sl.add(sub)、set.add(sub)に追加し、スキップする。
last = sl.get(sl.size() - 1)を入れ、
last.compareTo(sub) > 0の場合、
sl.remove(k - 1)、set.remove(last)を削除し、
sl.add(sub)、set.add(sub)を追加し、slをソートする。
それ以外は中断する。
WA2個。

# Main
Main02を元に、
sl.size() < kの場合、slをソートする。
AC 100ms

# Main\_fix
ソート済のTreeSetを使って書き直す。
また先頭k番目なので、setで管理するのは最大k文字あればよい。
aaaaaa -> aaaaa

TreeSet<String> setを用意し、
iを0からn回ループし、jを1からkまでループし、i+j>nの場合スキップする。
s1=s.substring(i, i+j)を入れ、!set.contains(s1)の場合、set.add(s1)に追加する。

itr=set.iterator()、ans=""を用意し、
iを0からk回ループし、ans=itr.next()を入れる。
ansを出力する。
AC 134ms

