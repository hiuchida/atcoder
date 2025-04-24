# Main01
n,mを読み込み、n<m/nの場合、-1を出力して、終了する。
mm=m、MyArray maを用意し、pr.lstの要素vをループし、
mm%v==0の場合、ma.add(v)を追加し、mm/=vを更新する。
ループ終了したら、mm>nの場合、-1を出力して、終了する。

ans=LONG\_MAX、k=1を用意し、k*k<mの間ループし、k++する。
aを1からkまでループし、a>nの場合中断する。
bをm/a-10からm/a+10までループし、b>nの場合スキップする。
a\*b>=mの場合、ansをa\*bの最小値にする。

ans==LONG\_MAXの場合、ans=-1を入れる。
ansを出力する。
WA13個。

# Main02
Main01を元に、
bをm/a-100からm/a+100に変更。
WA13個。

# Main
Main01を元に、
mm>nの場合、-1を出力して、終了する。　を削除。

