# Main
nを読み込み、int[][] aryに読み込み、ary[i]をソートする。
ans=0を用意し、iを0からn回ループし、b=ary[1][i]を用意する。
i1=binarySearch(ary[0], b)、i2=binarySearch(ary[2], b+1)を呼び出し、
x=i1、x*=n-i2を入れ、ans+=xを加える。
ansを出力する。

binarySearchの中で、
lt=0、rt=ary.length-1を用意し、
lt<=rtの間ループし、mid=(lt+rt)/2を入れ、
ary[mid]<xの場合lt=mid+1、それ以外はrt=mid-1を入れる。
ループ終了したらltを返す。
AC 704ms
