# Main01
nを読み込み、pr=new Prime(n+1)、ans=0を用意する。
xを1からnまでループし、ap1=calc(x)、ap=calc2(n, x, ap1)を呼び出す。
xx=(long)x*xを用意し、apの要素iでループし、j=xx/iを入れ、j>nの場合スキップする。
ans++をカウントする。
ループ終了したら、ansを出力する。

calc2の中で、int[] apの2乗のint\[\] ansを作成する。
Set<Integer> setを用意し、apの要素vをループし、apの要素v2をループし、
v3=(long)v*v2を入れ、v3<=nの場合、setにv3を追加する。
int[] ansを用意し、setの要素をans\[idx\]に入れる。
ansを返す。

calcの中で、nの約数int\[\] ansを作成する。
List<Integer> listを用意し、listに1を追加する。
n>1の間ループし、Set<Integer> setを用意し、nxt=pr.minf[n]、val=nxtを入れ、
n%nxt==0の間ループし、listの要素vをループし、setにv*valを追加する。
n/=nxt、val*=nxtを更新する。
listをsetから再作成する。
ループ終了したら、listをソートする。
int[] ansを用意し、listの要素をans\[i\]に入れる。
ansを返す。
TLE7個。

# Main02
Main01を元に、PrimeのList<Integer> list = new ArrayList<>();をコメントアウト。
Primeのcheck()もコメントアウト。
TLE6個。（test\_03.txtがAC）

# Main03
Main02を元に、calc2の中でelse break;を追加。
TLE7個。

# Main04
Main03を元に、calcの中で
Set<Integer> set=new HashSet<>(list);
を
List<Integer> lst=new ArrayList<>();
に変更。
set.add(v\*val);　を　lst.add(v\*val);　に変更。
list=new ArrayList<>(set);　を　list.addAll(lst);　に変更。
TLE6個。

# Main
Main04を元に、
Collections.sort(list);　を削除。
else break;　を削除。

# Main_fix
余計なコメントアウトを削除。

