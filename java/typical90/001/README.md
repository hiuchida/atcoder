# Main
kを二分探索で絞っていき、check(k)で判定する。

n,l,kを読み込み、int[] aryに読み込む。
ng=l+1、ok=-1を用意し、ok+1<ngの間ループし、
mid=ng+(ok-ng)/2を入れ、rc=check(mid)を呼び出し、
rcの場合ok=mid、ng=midを入れる。
ループ終了したら、okを出力する。

checkの中で、
pre = 0、c = 0を用意し、iを0からn回ループし、
ary[i]-pre >= xの場合、pre=ary[i]、c++を入れる。
l-pre >= xの場合、c++を入れる。
c >= k+1の場合trueを返す、それ以外はfalseを返す。
AC 396ms

# Main\_fix
書き直す。

ng=l+1、ok=-1を用意し、
を
ok=0、ng=l+1を用意し、
に修正。

AC 351ms

