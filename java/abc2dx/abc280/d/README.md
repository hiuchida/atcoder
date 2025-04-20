# Main01
kを読み込む。
long[] dpを用意し、dp[1]=1を初期化する。
iを2からdp.length;回ループし、dp[i]=dp[i-1]*iを更新する。
dp[i]<0の場合、中断する。（i=21でオーバーフローする）

iを2からdp.length;回ループし、x=dp[i]を入れ、
x%k==0の場合、iを出力し、終了する。
WA58個。

# Main02
pr=new Prime(M+1)、cnt=0、int[] aryを用意し、pr.listの要素vでループし、
k%v==0の間ループし、cnt++、ary[v]++、k/=vを更新する。
cnt==0の場合、kを出力する。

iを2から無限ループし、pr.factors(i)の要素vでループし、
ary[v]>0の場合、cnt--、ary[v]--を更新する。
cnt==0の場合、iを出力する。
WA10個。RE3個。

# Main03
Main02を元に、int cnt=0　を　long cnt=0　に変更。
WA10個。RE3個。

# Main04
Main03を元に、Primeのint[] factors　を　List<Integer> factors　に変更。
WA10個。RE3個。

# Main05
REの発生場所の調査。
Main04を元に、cnt==0以外の場合、-1を出力する。
WA61個。（REが消える）

# Main06
Main04を元に、MyArrayを追加し、List<Integer> factors　を　MyArray factors　に変更。
WA10個。RE3個。

# Main07
Main06を元に、
for (int i=2; true; i++)
を
for (int i=2; i<=M; i++)
に変更。
WA13個。（REが消える）

# Main08
Main07を元に、
cnt==0の場合、kを出力する。
の後に、
k>1の場合、kを出力する。
を追加。
WA3個。

# Main\_explain
解説の正解ソースをjavaに翻訳したもの。

# Main\_TestData,Main\_TestData2
Main08とMain\_explainの比較PGM。

発見したk=899627589169=948487^2
948487!は948487を1つしか使わないため、cnt>0が残る。

# Main11
Main08を元に、最後に、
iを2からMまでループし、ary[i]>0の場合、i\*iを出力する。
WA3個。

# Main12
Main11を元に、
ans=1を用意し、iを2からMまでループし、
ary[i]>0の場合、ans*=i、ans*=iを更新し、
ansを出力する。
WA3個。

# Main13
Main12を元に、
ary[i]>0の場合、jをary[i]回ループし、ans*=iを更新する。
WA3個。

# Main14
Main13を元に、
k>1の場合、kを出力する。
を削除。
WA13個。

# Main
Main13を元に、
ans=0を用意し、iを2からMまでループし、
ary[i]>0の場合、ans+=(long)i*(ary[i]+1)を更新する。

# Main\_fix
MyArrayを削除し、余計なコメントアウトを削除する。
実行時間が、202msから266msに延びた。

