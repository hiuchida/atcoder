# Main
n,kとint[] aa,abを読み込む。
boolean f0=true、f1=trueを初期値として、
iを0からn-2までループする。
a0=aa[i]、a1=aa[i+1]、b0=ab[i]、b1=ab[i+1]とし、
4通りの差の絶対値をd00、d01、d10、d11を計算する。
次の有効無効をboolean g0、g1として、
f0が有効ならばd00とd01がk以下なのか判定し、g0とg1を更新する。
同様にf1が有効ならばd10とd11を判定し、g0とg1を更新する。
g0==falseかつg1==falseはここで途切れるためng。
f0=g0、f1=g1としてループの先頭に戻る。
ループが終了したらok。

# Main\_fix
boolean f0,f1,g0,g1の代わりにint\[\]\[\] dpで履歴を残す。
初期値をdp\[0\]\[0\]=1、dp\[1\]\[0\]=1とし、dp\[0\]\[i\]やdp\[1\]\[i\]には
前のdp\[0\]\[i-1\]やdp\[1\]\[i-1\]からつながっている個数を入れる。
どちらも0の場合その時点で途切れるが、ループを打ち切らずi=n-1まで更新し、
最後のdp\[0\]\[n-1\]とdp\[1\]\[n-1\]でokかngか判定する。

# Main\_final
Main\_fixを元に、int[] aa,abをint[][] aryに変更することで、
fromを0,1、toを0,1にループし、
if (dp[from][i]>0) if (Math.abs(ary[from][i]-ary[to][i+1])<=k) dp[to][i+1]++;
と共通にできる。

