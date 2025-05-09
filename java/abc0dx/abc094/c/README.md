# Main01
nを読み込み、int[] iaに読み込む。
iを0からn回ループし、int[] na、ni=0を用意し、
jを0からn回ループし、i != jの場合、na[ni++] = ia[j]を入れる。
naをソートする。
mi=(n-1)/2を入れ、na[mi]を出力する。
TLE11個。

# Main
List<Info> lを用意し、l.add(new Info(i, ia[i]))に追加する。
lをソートする。
Set<Integer> s1,s2を用意し、
iを0からn/2回ループし、s1.add(l.get(i).idx)に追加する。
iをn/2からn/2回ループし、s2.add(l.get(i).idx)に追加する。
m1 = l.get(n/2).val、m2 = l.get(n/2-1).valを用意し、
iを0からn回ループし、s1.contains(i)の場合m1、それ以外はm2を出力する。
AC 729ms

# Main1
nを読み込み、List<Info> lに読み込み、lをvalをキーにソートする。
Set<Integer> s1,s2を用意し、iを0からn/2回ループし、s1.add(l.get(i).idx)を追加し、
iをn/2からn-1までループし、s2.add(l.get(i).idx)に追加する。
m1 = l.get(n/2).val、m2 = l.get(n/2-1).valを用意し、
iを0からn回ループし、s1.contains(i)の場合m1、それ以外はm2を出力する。
AC 628ms

# Main2
List<Integer> listに読み込み、List<Integer> tempにコピーしソートする。
min = temp.get(n / 2 - 1)、max = temp.get(n / 2)を入れ、
iを0からn回ループし、list.get(i) <= minの場合max、list.get(i) >= maxの場合minを出力する。
AC 1658ms

# Main3
Main2を元に、
iを0からn回ループし、list.get(i) <= minの場合max、それ以外の場合minを出力する。
AC 1656ms

# Main4
Main3を元に、
int[] aryとList<Integer> tempに読み込み、tempをソートする。
AC 1633ms

# Main\_fix
書き直す。（2018年はいつもList<Integer>をソートしていた。ラッパークラスの分だけ遅い）
int[] ary,ary2に読み込み、aryをソートする。
AC 1076ms

