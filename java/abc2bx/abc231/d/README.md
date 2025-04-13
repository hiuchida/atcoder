# Main01
a,bのカウントを取り、3つ以上が存在したらng。
n==mのときもループするのでng。
WA13個。

# Main02
Main01を元に、Node[] nodesにリンクを持たせて、3つ目のリンクが存在したらng。
WA13個。

Main01とチェック内容は変わらない。

# Main03
Main02を元に、n>mの場合でもループするので、check()とcheckNode()でループしたらng。
WA12個。

Main02とMain03のWAのジャッジデータが異なっている。

# Main04
Main03を元に、checkNode()の中にDeque<Integer> queを使ってループしたらng。
WA12個。

# Main
Main04を元に、checkNode()の中でNode nodeが変わっていなかったので、
引数をNode[] nodesに変えて、Node node=nodes[i]に変更する。
AC。

# Main\_fix
Mainを元に、Counterを廃止し、NodeをTreeSet<Integer> setに変更する。
link()の中で3つ目のリンクをチェックする。

# Main\_uf
Mainを元に、Nodeを廃止し、UnionFindに変更する。
uf.sameが見つかったらng。

# Main\_uf\_fix
Counter_int_int20250410を適用

