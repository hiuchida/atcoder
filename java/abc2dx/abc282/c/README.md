# Main
n,sを読み込み、char[] aryに展開する。bdq=falseを用意する。
iを0からary.length回ループし、
ary[i]=='\"'の場合、bdq=!bdqを更新する。
ary[i]==','の場合、!bdqの場合、ary[i]='.'を更新する。

aryから文字列ansを作成し、ansを出力する。
