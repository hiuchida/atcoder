# Main01
n,kを読み込み、n<kの場合1を出力して、終了する。
int[] ary、sum=0を用意し、k回ループし、ary[i]=1を初期化し、sum+=ary[i]を入れる。
iをkからnまでループし、ary[i]=sumを入れ、
sum-=ary[i-k]、sum+=ary[i]、sum%=Mを更新する。
ループ終了したら、ary[n]を出力する。
WA12個。

# Main02
Main01を元に、
途中でマイナスになっているので、sum%=Mの前に
c=1+Math.abs(sum/M)、sum+=M*cを追加する。
WA22個。

# Main
Main01を元に、
int[] ary、int sum　を　long[] ary、long sum　に変更する。
sum-=ary[i-k]　を　sum+=M-ary[i-k]　に変更する。
最後に　ary[n]　を　ans=ary[n]%M　に変更する。

