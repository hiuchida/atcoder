# Main_join
n-k以降とn-kまでを出力する。
List<Integer> listに貯めて、list.toString()から
replace("[", "").replace("]", "").replaceAll(",", "");
で余計な[,]を取り除くと、ブランク区切りの数値となる。

他でList<String> listに貯めて、String.join(" ", list)していたが、
使い分けをメモしておく。

ひょっとしてint[] ansで答えを入れて、String.join(" ", ans);で結合できるかと思ったが、
int[]は無理で、Stringでないとダメだった。

String.joinを使いたければ、List<String>かString[]に答えを入れる。
int[]のままの場合、Arrays.toStringから余計な[,]を取り除く。

int[]で答えが出ているときのために、String.joinと似せた自前のjoinを用意する。
String.joinがStringJoinerを呼んでいて、prefix="",suffix=""で、
StringBuilder valueに追加しているので、やっていることは同じ。
