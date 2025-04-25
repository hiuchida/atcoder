# Main01
nを読み込み、iを1から10までループし、
rc=query(sc, i+1)を呼び出し、rc==1の場合iを出力する。
rc=query(sc, n-i)を呼び出し、rc==0の場合n-iを出力する。
WA8個。

# Main02
nを読み込み、iを1から1までループし、
rc=query(sc, i+1)を呼び出し、rc==1の場合iを出力する。
rc=query(sc, n-i)を呼び出し、rc==0の場合n-iを出力する。

r=new Random()を用意し、q>1の間ループし、
idx=r.nextInt(n-3)+2を入れ、
rc1=query(sc, idx)、rc2=query(sc, idx+1)を呼び出し、
rc1!=rc2の場合idxを出力する。
WA8個。

# Main03
Main02を元に、
int[] aryを用意し、-1で初期化する。
ary[1]=0、ary[n]=1を入れる。
rc=query(sc, i+1)の結果をary[i+1]=rcに入れる。
rc=query(sc, n-i)の結果をary[n-i]=rcに入れる。

idx=r.nextInt(n-3)+2を入れ、ary[idx]>=0 || ary[idx+1]>=0の場合スキップする。
rc1=query(sc, idx)の結果をary[idx]=rc1に入れる。
rc2=query(sc, idx+1)の結果をary[idx+1]=rc2に入れる。
WA8個。

# Main04
Main03を元に、
idx=r.nextInt(n)+1を入れる。
rc1=ary[idx]を入れ、rc1<0の場合、rc1=query(sc, idx)を入れる。
rc2=ary[idx+1]を入れ、rc2<0の場合、rc2=query(sc, idx+1)を入れる。
WA8個。

# Main11
lt=1、rt=nを用意し、ary[lt]=0、ary[rt]=1を入れる。
無限ループし、lt==rtの場合ltを出力する。
mid=(lt+rt)/2を入れ、rc=query(sc, mid)を呼び出し、ary[mid]=rcに入れる。
rc==0の場合lt=mid、それ以外はrt=midを入れる。
WA20個。

# Main12
Main11を元に、
lt<rtの間ループする。

ループ終了したら、
ary[lt]<0の場合、ary[lt]=query(sc, lt)を入れる。
ary[rt]<0の場合、ary[rt]=query(sc, rt)を入れる。
ary[lt]!=ary[lt+1]の場合、ltを出力する。
WA20個。

# Main
Main12を元に、
lt<rt　を　lt+1<rt　に変更。

一般的な二分探索が、lt=mid+1、rt=mid-1としているのでループ条件がlt<rtとしている。
lt=mid、rt=midの場合、ltが0の右端、rtが1の左端を位置するので、lt+1==rtで無限ループして、
クエリの上限ですべてWAとなっていた。

# Main\_Test
Mainのnew RuntimeException();は記述ミスだが、
以下のランタイムエラーを意図的に発生しようとしてもREとならずにWA20個となった。
throw new RuntimeException();
long x=1/0;
long x=Integer.parseInt("123456789012345678");

以下の無限ループにしたら、TLE20個で区別がついた。
while (true) ;

