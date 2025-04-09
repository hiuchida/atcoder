# Main01
n,mを読み込み、Counter cntとint[] indegを用意する。
iを0からm-1までループし、a,bをcnt.add(a, b)に追加し、indeg[b]にカウントする。
PriorityQueue<Integer> queを用意する。
iを1からnまでループし、indeg[i]==0のときにqueへ追加する。
List<Integer> listを用意する。
queが空になるまでループする。
xをqueの先頭から取出し、listへ追加する。
cnt.get(x)の各要素nxtに対してindeg[nxt]を-1し、indeg[nxt]==0のときqueへ追加する。
ループが終了し、listのサイズがnでない場合は-1を出力する。
それ以外はlistの全要素を出力する。
WA11個。

# Main
Mainを元に、Counter内のTreeSet<Integer>をList<Integer>に変更する。

入力データが以下のように同じ組み合わせが複数存在することがあり、
TreeSetでは1件となってしまい、indeg\[nxt\]が-1しかされず残ってしまう。
9 3
1 2
1 2
1 2

