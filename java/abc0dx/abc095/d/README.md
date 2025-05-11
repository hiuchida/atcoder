# Main01
n,cを読み込み、long[] ix,ivに読み込む。
long[] ransを用意し、iを0からn回ループし、
px = (i == 0) ? 0 : ix[i - 1]、rans[i] = iv[i] - (ix[i] - px)を入れる。
long[] lansを用意し、iを0からn回ループし、
px = (i == 0) ? c : ix[n - i]、lans[i] = iv[n - i - 1] - (px - ix[n - i - 1])を入れる。
mans = 0を用意し、iを0からn回ループし、
ans = 0を用意し、jを0からj <= iまでループし、ans += rans[j]を加え、mans < ansの場合mans = ansを入れる。
ans -= ix[i]を入れ、jを0からj < n - i - 1までループし、ans += lans[j]を加え、mans < ans)の場合mans = ansを入れる。
iを0からn回ループし、ans = 0を用意し、jを0からj <= iまでループし、ans += lans[j]を加え、mans < ansの場合mans = ansを入れる。
ans -= c - ix[i]を入れ、jを0からj < n - i - 1までループし、ans += rans[j]を加え、mans < ans)の場合mans = ansを入れる。
mansを出力する。
WA11個。TLE18個。

# Main02
Main01を元に、
ans -= ix[i]を入れ、
を
ans -= c - ix[n - i - 1]
に修正。
TLE18個。

# Main03
Main02を元に、
mr = 0、an = 0を用意し、an += rans[i]を加え、mr < anの場合mr = anを入れる。
ml = 0、an = 0を用意し、an += lans[i]を加え、ml < anの場合ml = anを入れる。

ans + ml > mansの場合に、jを0からj < n - i - 1までループする。

ans + mr > mansの場合に、jを0からj < n - i - 1までループする。
TLE18個。

# Main11
書き直す。

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

# Main12
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

