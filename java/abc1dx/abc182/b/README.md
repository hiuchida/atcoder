# Main
nを読み込み、int[] aryに読み込む。
max=0を用意し、n回ループし、maxにaの最大値を更新する。

ans=0、gcd=0を用意し、kを2からmaxまでループし、
c=0を用意し、iを0からn回ループし、ary[i]%k==0ならば、c++をカウントする。

gcd<cならば、gcd=c、ans=kを更新する。

ansを出力する。
