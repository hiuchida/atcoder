# Main01,Main02
int[] cnt,cnt2を用意し、初期値cnt[ary[0]]=1;。
cnt2のj+ary\[i\]とj\*ary\[i\]にcnt\[j\]をコピーする。
cntとcnt2を入れ替えて、cnt2をクリアする。
WA5個。
intのためオーバーフロー。

# Main
long[] cnt,cnt2を用意し、初期値cnt[ary[0]]=1;。
cnt2のj+ary\[i\]とj\*ary\[i\]にcnt\[j\]をコピーする。
cntとcnt2を入れ替えて、cnt2をクリアする。

# Main\_dp
2次元配列dp\[n+1\][10]を用意する。
初期値dp\[1\]\[ary\[0\]\]=1;。
dp\[i+1\]\[x\]のj+ary\[i\]とj\*ary\[i\]にdp\[i\]\[j\]をコピーする。
