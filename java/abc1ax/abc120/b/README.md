# Main
a,b,kを読み込み、List<Integer> listを用意する。
iを1からmin(a, b)までループし、
a%i==0 && b%i==0の場合、list.add(i)を追加する。

listをソートし、list.get(list.size()-k)を出力する。
