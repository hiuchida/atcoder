# Main
s,nを読み込み、char[] aryに展開する。
n回ループし、l,rを読み込み、char[] ary2にaryをコピーする。
jをlからrまでループし、i2=r-j+lを入れ、ary2[j-1]=ary[i2-1]をコピーする。
ary=ary2に入れ替える。
new String(ary)を出力する。
