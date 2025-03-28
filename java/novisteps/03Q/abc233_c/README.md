# Main01
n,xを読み込み、List<List<Integer>> listにx/a*a==xとなるaを読み込む。
List<Long> setにlist.get(0)の各要素をint→long変換してコピーする。（変数名はSetで作った後に、重複を許すためListに変更したため）
dfs(1, set)を呼び、ansを出力する。

dfs内でi==nならばsetの各要素がxの場合をカウントする。
それ以外の場合、list.get(i)の各要素とsetの各要素の積yを計算する。
y<=x && x/y*y==xの場合、List<Long> set2にyを追加する。
RE3個。

# Main02
Main01を元に
・List<List<Integer>> listをList<List<Long>> listに変更
・List<Integer> list0=list.get(i);をList<Long> list0=list.get(i);に変更
RE3個。

# Main03
Main02を元に、for (long k : set) {の外側でif (set.size()>0) {を追加
RE3個。

# Main04
Main03を元に、if (x/a*a==x) list0.add(a);をlist0.add(a);に変更
RE3個。

# Main
Main04を元に、if (y<=x && x/y*y==x) set2.add(y);をif (y<=x) set2.add(y);に変更
AC

y==0となったため、x/y\*yの部分で0で割っていた。
そもそもy=j*kの時点でy==0となったということは、既にオーバーフローしているので、
if (j*k<=x)のチェックは正確ではないが、オーバーフロー後の計算値でk==xとならなかったため正解となった。

# Main\_fix
Mainを元に、long y=j*k;の前に、if (j>x/k) continue;を追加

# Main\_test
Mainを元に、long y=j*k;の後にlong test=1/y;を追加し、RE4個となることを確認

# Main\_long
Main\_fixを元に、dfsにList<Long> setをlong valに変更する

