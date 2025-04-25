# Main
n,sを読み込み、char[] aryに展開し、ans=-1を用意する。
iを0からn回ループし、
ary[i]=='o'の場合、
blt=falseを用意し、i>0 && ary[i-1]=='-'の場合、blt=trueをセットする。
jをi+1からn-1までループし、ary[j]!='o'の場合中断する。
brt=falseを用意し、j<n && ary[j]=='-'の場合、brt=trueをセットする。
blt || brtの場合、ansをj-iの最大値に更新する。
i=j-1を入れる。

ansを出力する。
