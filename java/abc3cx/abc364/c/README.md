# Main
最初Pointクラスに入れ、a+bをキーにしてソートしたので、List<Point>を用意した。
その後、aによるソート、bによるソートで、それぞれ最大値から個数を数えればよかった。

このため、int[] a,bをソートすればよかったが、Pointのst,edのそれぞれでソートしていた。

# Main_fix
余計なList<Point>を削除。
昇順に並べているので、n-1からカウントする。
