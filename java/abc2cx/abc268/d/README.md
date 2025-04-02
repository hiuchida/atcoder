# Main01
n,mを読み込み、String[] aryを読み込み、すべてのs.lengthの合計をlenに計算する。
区切りがすべて1文字の場合のlen+=n-1を加える。
n==1の場合、sを読み込み、s.equals(ary[0])の場合-1を出力する。
ループが終了したらary[0]を出力する。
Set<String> setを用意し、sを読み込み、setに追加する。
List<String> list、boolean[] flagを用意し、dfsを呼び出す。
戻ってきたら-1を出力する。

dfsの中で、
list.size()==nならば、listを結合してsを作成し、setに含まれていない場合はsを出力する。
それ以外は再帰呼び出しを中断する。
iを0からn-1までループし、flag[i]==trueならスキップする。
flag[i]=trueにセットし、
list.size()<n-1の場合、jを0からj+len+us<=16までループし、s=ary[i]に入れ、末尾にk+1個の'\_'を追加する。
listにsを追加し、dfsを呼び出し、listの末尾を取り除く。
list.size()==n-1の場合、s=ary[i]に入れ、listにsを追加し、dfsを呼び出し、listの末尾を取り除く。
flag[i]=falseにリセットする。
WA2個。

# Main02
Main01を元に、list.size()==nのときに、以下のチェックを追加する。
if (s.length()<3 || s.length()>16) return;
WA2個。

# Main03
Main02を元に、
for (int j=0; j+len+us<=16; j++) {
を
for (int j=0; j+len+us<=16+1; j++) {
に修正する。
WA2個。

# Main

