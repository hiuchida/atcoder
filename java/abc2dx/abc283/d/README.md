# Main
sを読み込み、char[] aryに展開する。boolean[] flag、List<Integer> stackを用意する。
iを0からary.length回ループし、
ary[i]=='('の場合、stackに0+ary[i]を追加する。
ary[i]==')'の場合、無限ループし、vにstackの末尾を取出し、v=='('の場合中断する。
flag[v]=falseを更新する。
ary\[i\]が'('、')'以外の場合、stackに0+ary[i]を追加する。
flag[ary[i]]の場合ng。
flag[ary[i]]=trueを更新する。

ループ終了したらok。
