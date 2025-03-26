# Main
n,qを読み込み、ary\[\]に読み込む。
先にx,kをax\[\],ak\[\]に読み込み、setにaを追加する。
iを0からn-1までループし、aがsetに含まれている場合、add()を呼び出しTreeMap<Integer, List<Integer>> mapに追加する。
iを0からq-1までループし、mapにxが存在し、set.size>=kの場合、setのk-1の値を出力する。
それ以外は-1を出力する。

add()の中で、mapにaが存在しない場合、setを生成し、mapに追加する。
setにiを追加する。

