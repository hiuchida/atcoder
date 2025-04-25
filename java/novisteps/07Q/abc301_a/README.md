# Main
n,sを読み込み、char[] aryに展開し、ans=""、t=0、a=0を用意する。
iを0からary.length回ループし、
ary[i]=='T'の場合、t++し、t>aの場合、ans="T"を入れる。
それ以外の場合、a++し、a>tの場合、ans="A"を入れる。
ansを出力する。
