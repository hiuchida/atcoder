# Main
lineを読み込み、prev = line.charAt(0)、cnt = 0を用意する。
iを0からline.length()回ループし、
ch = line.charAt(i)を入れ、
prev != chの場合、cnt++、prev = chを入れる。
cntを出力する。
AC 121ms

# Main\_fix
書き直す。

sを読み込み、char[] aryに展開する。
n==1の場合、0を出力し、終了する。
cb=0、cw=0を用意し、iを1からary.length-1までループし、
ary[i-1]!=ary[i]の場合、
ary[i-1]=='B'の場合cb++、それ以外はcw++を加える。
cb+cwを出力する。
AC 121ms

