# Main
int[] aryに5件読み込む。
min=10、sum=0を用意し、
iを0から5回ループし、
ary[i]%10!=0の場合、minにary[i]%10の最小値を更新する。
sum+=ary[i]を加える。
ary[i]%10!=0の場合、sum+=10-ary[i]%10を加える。

sum-10+minを出力する。
