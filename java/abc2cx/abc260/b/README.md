# Main
n,x,y,zを読み込み、Point[] aryにaとbと別々に読み込む。
TreeSet<Integer> setを用意し、
まずp.st,p.idxをキーにソートする。
cnt=0を用意し、iを0からn-1までループし、cnt==xとなったら打ち切る。
ary[i].idxがsetに存在したらスキップする。
setに追加し、cnt++する。
同様に、p.ed,p.idxをキーにソートし、cnt==yとなるまでsetに追加する。
同様に、p.st+p.ed,p.idxをキーにソートし、cnt==zとなるまでsetに追加する。
setの全要素を出力する。
