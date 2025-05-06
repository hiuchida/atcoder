# Main01
nを読み込み、281までの素数の配列int p[]を用意する。
iを0からn-2までループし、p[i]を出力する。
p[n - 1]を出力する。
WA4

# Main02
Main01を元に、
int[] iaを用意する。
iを1からn-1までループし、ia[i]を出力する。
ia[n]を出力する。
RE8個。（ArrayIndexOutOfBoundsException）

# Main03
Main01を元に、
iを1からn-1までループし、p[i]を出力する。
p[n]を出力する。
WA6個。

# Main04
Main01、Main03を元に、
n%2==0の場合、Main03の処理
それ以外の場合、Main01の処理
WA6個。

# Main05
initの中で、List<Integer> plに2以上55555以下の素数を追加する。
List<Integer> lを用意し、l.size() < nの間ループし、
idx = (int) (Math.random() * pl.size())、pp = pl.get(idx)を入れ、
l.contains(pp)の場合スキップする。
l.add(pp)を追加する。

check(l)がtrueならば、lを出力する。

checkの中で、
sum = 0を用意し、iを0から5回ループし、sum += l.get(i)を入れ、
isPrime(sum)の場合falseを返す。
iを5からl.size()-1までループし、sum -= l.get(i - 5)、sum += l.get(i)を入れ、
isPrime(sum)の場合falseを返す。
ループ終了したらtrueを返す。
WA6個。

# Main06
Main05を元に、
checkの中で、
iを0からl.size() - 5までループし、
jをi+1からl.size() - 4までループし、
kをj+1からl.size() - 3までループし、
mをk+1からl.size() - 2までループし、
nをm+1からl.size() - 1までループし、
sum = l.get(i) + l.get(j) + l.get(k) + l.get(m) + l.get(n)を入れ、
isPrime(sum)の場合falseを返す。
TLE2個。

# Main07
Main06を元に、
plに3以上55555以下の素数を追加する。
TLE2個。

# Main08
Main07を元に、
List<Integer> lの代わりにSet<Integer> sに変更。
l.contains(pp)の判定をせずに、常にs.add(pp)を追加する。
TLE2個。

# Main09
Main08を元に、
plに2以上55555以下の素数を追加する。
n > 5の場合、s.add(2)を追加する。
TLE2個。

# Main10
Main09を元に、
checkの前に、List<Integer> lをソートする。
checkの中で、l.get(i) == 2の場合スキップする。
TLE2個。

# Main11
Main10を元に、
n > 6の場合、s.add(5)を追加する。
n > 7の場合、s.add(7)を追加する。
TLE2個。

# Main
plに11以上55555以下の素数を追加する。
List<Integer> lを用意し、
iを0からpl.size()回ループし、
p = pl.get(i)を入れ、p % 5 == 1の場合、l.add(p)を追加する。
AC 88ms

# Main2
r26からr27に変更。
Prime prime、StringBuilder sbを用意し、
iを11からn>0の間+5ループし、
prime.isPrime(i)の場合、sb.append(i)に追加し、n--する。
AC 75ms

# Main\_fix
PrimeMini20250425を適用。

pr.lstの要素vをループし、n==0の場合中断する。
v%5==1の場合、vを出力する。
AC 94ms

