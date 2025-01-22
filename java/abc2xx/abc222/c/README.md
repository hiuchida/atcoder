# Main
Point(st,ed)のソートキーをst降順、ed昇順にする。
Point(0,i)で初期化する。
隣合うpt1,pt2のedのary\[\]のj文字目の勝敗を判定する。
booleanを返すので、ch1,ch2とch2,ch1の2回渡して、
左側が勝利したらtrueのため、pt.st++;する。
ap\[\]は0baseだが、ary\[\]は1baseのため、pt.edも1baseで管理する。

# Main\_static
Point(st,ed)のedは添字のため、stに相当する配列をstatic int\[\] win;とすると、
ソート対象はInteger\[\] order;で管理できる。
ただし、int\[\] order;ではComparatorを定義できないため、使えない。
Arrays.sort(ap);の代わりに、Arrays.sort(order, new Comparator<Integer>() {とし、
win\[\]を第1ソートキー、引数o1を第2ソートキーとする。
