# Main01
nを読み込み、int[] aryに3件読み込み、ソートする。
iを0から3回ループし、n==ary[i]ならばng。
ary[0]+1==ary[1] && ary[1]+1==ary[2]ならばng。
cnt=0を用意し、n>0の間ループする。
a=n-3を入れ、binarySearch(ary, a)が存在しない場合、n=a、cnt++し、スキップする。
b=n-2を入れ、binarySearch(ary, b)が存在しない場合、n=b、cnt++し、スキップする。
c=n-1を入れ、binarySearch(ary, c)が存在しない場合、n=c、cnt++し、スキップする。
それ以外はng。
ループ終了したら、cnt>100の場合ng、それ以外はok。
WA1個。

# Main
Main01を元に、
int[] ary　の代わりに　Set<Integer> set=new HashSet<>()　に変更する。

このときに無意識に
ary[0]+1==ary[1] && ary[1]+1==ary[2]ならばng。
を削除した。

# Main01\_fix
Main01を元に、
ary[0]+1==ary[1] && ary[1]+1==ary[2]ならばng。
を削除。

NG数字が11,12,13と並んでいても、例えばN=9からスタートだと、邪魔にならないのでokのはずが、ngとなる。

