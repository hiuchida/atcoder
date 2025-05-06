# Main01
package abc035.b;を消し忘れ。
RE62個。

# Main
s,tを読み込み、p = new Point(0, 0)、q = 0を用意する。
iを0からs.length()回ループし、ch = s.charAt(i)を入れ、
ch=='L'の場合、p.x--
ch=='R'の場合、p.x++
ch=='U'の場合、p.y++
ch=='D'の場合、p.y--
ch=='?'の場合、q++
d = p.getManhattanDistance(new Point(0, 0))を用意し、
t==1の場合、d + qを出力する。
t==2の場合、d >= qの場合d - q、それ以外はq -= dを入れq % 2を出力する。

# Main\_fix
書き直し。

