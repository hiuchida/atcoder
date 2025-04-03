# Main
nを読み込み、int[] aryに読み込み、TreeSet<Integer> set、Counter cntを用意し、
setとcntにary[i]を追加する。
List<Integer> listにsetを入れ、TreeMap<Integer,Integer> mapを用意する。
listサイズループし、mapに(v, list.get(i)を追加する。
n回ループし、v=map.get(i)を入れ、v==nullならばv=0に置き換え、
cnt.get(v)を出力する。
