# Main01
穴埋め問題を解く。

nを読み込み、int[] aryに読み込む。
int[] tmpを用意し、MergeSort(ary, tmp, 1, n+1)を呼び出す。
ary[i]を出力する。

MergeSortの中で、
lt+1==rtの場合戻る。
mid=(lt+rt)/2を入れ、MergeSort(ary, tmp, lt, mid)、MergeSort(ary, tmp, mid, rt)を呼び出す。
c1=lt、c2=mid、cnt=0を用意し、
c1!=mid || c2!=rtの間ループし、
c1==midの場合、tmp[cnt]=ary[c2]、c2++を入れる。
c2==rtの場合、tmp[cnt]=ary[c1]、c1++を入れる。
それ以外の場合、ary[c1]<=ary[c2]の場合tmp[cnt]=ary[c1]、c1++を入れる。
それ以外の場合tmp[cnt]=ary[c2]、c2++を入れる。
cnt++を加える。

iを0からcnt回ループし、ary[lt+i]=tmp[i]を入れる。
TLE2個。

# Main
Main01を元に、入力データ読み込みをScannerからBufferedReaderに置き換える。

in21\_large 988 -> 852
in22\_large 995 -> 754
in23\_large 992 -> 845
in24\_large 995 -> 839
in25\_large 992 -> 755
in26\_large 995 -> 749
in27\_large 998 -> 760
in28\_large 999 -> 841
in29\_large 1019 -> 748
in30\_large 1006 -> 766
