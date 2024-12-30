# Main
long[] sum = new long[n+1];を思いついたが、バイナリーサーチで値と値の間が見つかったときに
どこのsumを使うか迷って、long[2*10^9+1]に書き換えようとして、OutOfMemoryで終わる。

long[n+1];に戻して、例1がちょうどよいテストデータだったので、添字の調整は出来た。

例1
aryx[] = [1 3 5 7]
aryp[] = [1 2 3 4]
sum[] = [0, 1, 3, 6, 10]

l=0, r=3のとき、sum[2]-sum[0]で計算できるように、sum[0], sum[1]=p[0]+sum[0]とする。

rc=Arrays.binarySearch(aryx, x)
x=0 rc=-1
x=1 rc=0
x=2 rc=-2
x=3 rc=1
x=4 rc=-3
x=5 rc=2
x=6 rc=-4
x=7 rc=3
x=8 rc=-5

leftは、rc>=0のとき、rc+1-1=rc。rc<0のとき、-(il+1)+1-1=-(il+1)。
rightは、rc>=0のとき、rc+1。rc<0のとき、-(ir+1)。
