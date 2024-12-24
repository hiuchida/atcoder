# Main
得点順、同点の場合は名前順のため、InfoクラスにComparableを実装する。
ArrayList<Info>に追加し、最後にソートする。

# Main_array
int a,b,c,d,e;をint[] ary;に置換する。
4ビット目(2^4=16)を添字0として、int mask = 16 >> i;でビットテストの値を用意する。
名前を"ABCDE".charAt(i)で取得する。

if ((v & mask) > 0)は厳密にはif ((v & mask) == mask)だが、
maskが1ビットしか立っていないので同じ結果となる。
