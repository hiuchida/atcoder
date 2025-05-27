# Main
n,xを読み込み、int[] aryに読み込み、ソートする。
lt=0、rt=n-1を用意し、lt<=rtの間ループし、mid=(lt+rt)>>>1を入れ、
ary[mid]==xの場合ok。
ary[mid]<xの場合lt=mid+1を入れる。
それ以外はrt=mid-1を入れる。

ループ終了したらng。
