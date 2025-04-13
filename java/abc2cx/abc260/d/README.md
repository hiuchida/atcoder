# Main
n,kを読み込み、int[] aryに読み込む。
Counter cnt、int[] ansを用意する。
iを0からn-1までループし、a=ary[i]を入れる。
a以上の最小値key=cnt.set.ceiling(a)を探す。
key==nullの場合、
k==1の場合はその場で捨てるのでans[a-1]=i+1に記録。それ以外はcntにa,aを追加する。
key!=nullの場合、
lst=cnt.get(key)を入れて、lstにaを追加する。
lstのサイズがkとなったらlstの全要素をans[v-1]=i+1に記録、cntからkeyを取り除く。
それ以外は、cntからkeyを取り除き、a,lstを追加する。
ループが終了したら、ans\[i\]を出力する。

# Main\_fix
Counter_int_listint20250413を適用
Integer key=cnt.set.ceiling(a)　を　Integer key=cnt.keySet().ceiling(a)　に変更。

