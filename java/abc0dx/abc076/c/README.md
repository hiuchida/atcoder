# Main
s,tを読み込み、char[] as,atに読み込み、List<String> listを用意する。
iを0からas.length回ループし、
comp(as, i, at)を呼び出し、trueならばss=make(as, i, at)を入れ、list.add(ss)に追加する。
listをソートし、listが空の場合"UNRESTORABLE"、それ以外はlist.get(0)を出力する。

compの中で、
jを0からat.length回ループし、i>=as.lengthの場合falseを返す。
as[i]!='?' && as[i]!=at[j]の場合falseを返す。
i++を加える。
ループ終了したらtrueを返す。

makeの中で、
char[] aryを用意し、asをコピーする。
jを0からat.length回ループし、ary[i+j]=at[j]を入れる。
jを0からary.length回ループし、ch=ary[j]を入れ、
ch=='?'の場合ch='a'を置き換える。
ary[j]=chを入れる。
new String(ary)を返す。
