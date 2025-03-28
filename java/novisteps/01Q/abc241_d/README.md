# Main
qを読み込み、iを0からq-1までループする。
c,xを読み込み、c==1の場合add(x)を呼び出す。
それ以外の場合、kを読み込み、c==2の場合low(x,k)、c==3の場合high(x,k)を呼び出す。

add()の中で、v=map.get(x)が存在しない場合、mapに(x,1)を追加する。
それ以外の場合、mapに(x,v+1)を更新する。

low()の中で、val0=map.get(x)が存在する場合、k=k-val0し、k<=0の場合xを出力する。
無限ループし、key=map.lowerKey(x)でx未満の最大値を検索し、存在しない場合-1を出力する。
それ以外の場合、val=map.get(key)を得て、k=k-valし、k<=0の場合keyを出力する。
x=keyに置き換えループ先頭へ戻る。

high()の中で、low()のlowerKey()をhigherKey()に置き換えて、同様の処理を行う。
