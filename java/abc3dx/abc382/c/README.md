# Main
TreeMapに美食度をkeyとして誰iを追加し、TreeMapのkeyをlistとしてバイナリーサーチし、見つからない場合、挿入ポイントから一番近い左のkeyを取得する。
もし挿入ポイントが一番左の場合、美味しさ＜美食度のため-1を出力する。
keyに格納されている誰iを出力する。

# Main_array
int\[\]に誰iを右端から詰めて、左端は0のため-1を出力する。

# Main_pair
MainのTreeMapのkey, valueをPairに変え、List<Pair> listをソート、バイナリーサーチする。
PairはcompareTo()の実装が必要。

# Main_floorKey
TreeMapがNavigableMapを実装していることを利用すれば、
keyをバイナリーサーチして見つからないときに挿入ポイントから一番近いkeyを探す部分が
NavigableMap.floorKey()に置換できる。

もし一番左の場合nullが返るので、intでなくIntegerで受ける必要がある。
