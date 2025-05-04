# Main
nを読み込み、String[] aryに読み込む。
Set<String> setを用意し、set.contains(s)の場合ng、それ以外はset.add(s)に追加する。

iを1からn-1までループし、s1=ary[i-1]、s2=ary[i]、
ch1=s1.charAt(s1.length()-1)、ch2=s2.charAt(0)を入れ、ch1!=ch2の場合ng。

ループ終了したらok。
