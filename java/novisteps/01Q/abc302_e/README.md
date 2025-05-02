# Main01
n,qを読み込み、Counter cntを用意する。
q回ループし、cを読み込み、
c==1の場合、cnt.add(u, v)、cnt.add(v, u)を追加する。
c==2の場合、cnt.get(v)の要素uでループし、cnt.del(u, v)を削除する。
cnt.remove(v)を削除する。
n-calc(cnt)を出力する。

calcの中で、ans=0を用意し、
cnt.keySet()の要素uでループし、
cnt.get(u).size()>0の場合ans++をカウントする。
ansを返す。
TLE26個。

# Main02
Main01を元に、
c==2の場合、cnt.get(v)の要素uでループし、cnt.del(u, v)を削除し、
cnt.get(u).size()==0の場合、cnt.remove(u)を削除する。

calc(cnt)の代わりにcnt.size()を使う。
TLE3個。

# Main03
Main02を元に、
ans=nを用意し、
c==1の場合、cnt.get(u).size()==0やcnt.get(v).size()==0の場合ans--
c==2の場合、cnt.get(u).size()==0の場合ans++
cnt.get(v).size()>0の場合ans++
TLE16個。

# Main04
Main03を元に、int型をInteger型に変更する。
Integer u
Integer v
get(Integer k)
remove(Integer k)
is(Integer k, Integer idx)
add(Integer k, Integer idx)
del(Integer k, Integer idx)
TLE3個。

# Main05
Main02を元に、int型をInteger型に変更する。
Main04と同様。
TLE3個。

# Main06
Main05を元に、int size(Integer k)を追加する。
TLE15個。

# Main07
Main04を元に、int size(Integer k)を追加する。
TLE10個。

# Main08
Main07を元に、int size(Integer k)を追加する。
TLE12個。

# Main09
Main08を元に、TreeSetをHashSetに変更する。
TLE2個。

# Main10
Main09を元に、Integer.valueOf(sc.nextInt())に変更する。
TLE3個。

# Main
Scanner sc=new Scanner(System.in)
を
BufferedReader in=new BufferedReader(new InputStreamReader(System.in))
に置き換え。
AC 1422ms

# Main01\_Test
Main01を元に、BufferedReader inに置き換え。
TLE25個。

# Main02\_Test
Main02を元に、BufferedReader inに置き換え。
AC 1709ms->1847ms

# Main03\_Test
Main03を元に、BufferedReader inに置き換え。
AC 1834ms

