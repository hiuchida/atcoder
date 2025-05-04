# Main
h,wを読み込み、String[] aryに読み込む。
list=removeLine(ary)を呼び出し、
sb=removeColumn(list)を呼び出し、
sb[i]を出力する。

removeLineの中で、
List<String> ansを用意し、
aryの要素sでループし、bok=trueを用意し、
xを0からs.length()回ループし、s.charAt(x)=='#'の場合bok=falseにリセットする。
!bokの場合、ans.add(s)を追加する。
ansを返す。

removeColumnの中で、
StringBuilder[] ansを用意し、ans[i]=new StringBuilder()を初期化する。
xを0からlist.get(0).length()回ループし、bok=trueを用意し、
yを0からlist.size()回ループし、list.get(y).charAt(x)=='#'の場合bok=falseにリセットする。
!bokの場合、yを0からlist.size()回ループし、ans[y].append(list.get(y).charAt(x))を追加する。
ansを返す。
