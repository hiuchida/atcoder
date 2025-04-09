# Main01
UnionFindで管理しようとして、磁石をまたいで繋がってしまった。

# Main
磁石に接しない範囲をUnionFindで管理する。
隣り合う2点の両方がcheck()を満たす場合にuf.merge()する。
Map<Integer, Integer> countにufのrootをキーとして、カウントを登録する。
隣り合う2点がuf.rootが異なる場合、edge.get(r).setにuf外のaddr値を登録する。
edge.get(r).setは上下、左右の2方向から追加される場合があるので、Setで重複を省く。
countのカウントと、edge.setの件数の和の最大値を求める。
