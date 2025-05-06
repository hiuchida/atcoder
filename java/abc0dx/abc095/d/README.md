# Main01
n,cを読み込み、long[] ax、int[] avに読み込む。
long[] sum1を用意し、sum1[i+1]=sum1[i]+av[i]を集計する。
long[] ay、long[] sum2を用意し、
j=n-1-iを入れ、ay[i]=c-ax[j]を入れ、sum2[i+1]=sum2[i]+av[j]を集計する。

ans=0を用意し、iを1からnまでループし、
v1=sum1[i]-ax[i-1]を入れ、ansをvの最大値に更新する。
jを1からi+j<=nまでループし、
v2=sum2[j]-ay[j-1]、z=min(ax[i-1], ay[j-1])を入れ、
ansをv1+v2-zの最大値に更新する。

ansを出力する。
WA6個。TLE18個。

# Main02
Main01を元に、配列のサイズをすべてn+1にする。
i==0,j>0やi>0,j==0の両方を2重ループでカバーする。

iを1からnまでループし、
を
iを0からnまでループし、
に修正。

jを1からi+j<=nまでループし、
を
jを0からi+j<=nまでループし、
に修正。
TLE18個。（部分点）

# Main

