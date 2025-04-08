# Main
s,tを読み込み、char\[\] as,atに展開し、ソートする。
n=at.lengthを用意し、iを0からn/2回ループする。
at[i]とat[n-1-i]をスワップする。
as,atから文字列ss,stを作成する。
ss.compareTo(st)<0ならばok、それ以外はng。
