# Main01
nを読み込み、pr.check(n)ならば0を出力して、終了する。
cnt=0を用意し、pr.lstの要素pをループし、
n%p==0の場合、cnt++、n/=pを更新する。
(cnt+1)/2を出力する。
WA15個。

# Main02
Main01を元に、
int cnt,ansをlong cnt,ansに変更。
WA15個。

# Main03
Main01を元に、
int ans=(cnt+1)/2の代わりに、
MyArray maを用意し、ma.add(cnt)を追加し、ans=0を用意し、
無限ループし、v=ma.array[i]を入れ、
v>1の場合、ma2.add(v/2)、ma2.add(v-v/2)を追加する。
それ以外の場合、ma2.add(v)を追加する。
ma.size==ma2.sizeの場合中断する。
ans++をカウントし、ma=ma2を入れる。
WA13個。

# Main04
Main01を元に、
int ans=(cnt+1)/2の代わりに、
x=1を用意し、x<cntの間ループし、ans++、x*=2を更新する。
WA13個。

# Main
1000003は素数で0だが、
2000006は1000003\*2だが、10^6以下の素数で割ると、n=1000003が残るため、cnt++する必要がある。

Main04を元に、
n>1の場合、cnt++を加える。
AC

# Main03\_Test
Main03を元に、
n>1の場合、cnt++を加える。
AC

