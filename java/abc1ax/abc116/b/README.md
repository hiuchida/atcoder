# Main
sを読み込み、int[] aryを用意し、ary[1]=sを初期化する。
iを2からNまでループし、a=ary[i-1]を入れ、
a%2==0の場合ary[i]=a/2、それ以外はary[i]=3*a+1を入れる。

Set<Integer> setを用意し、iを1から無限ループし、
a=ary[i]を入れ、set.contains(a)に存在する場合iを出力して終了する。
set.add(a)を追加する。
