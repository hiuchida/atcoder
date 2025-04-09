# Main01
int[] aryb,arywに読み込み、昇順にソートし、
long[] sumb,sumwに大きい方からの累積和を計算する。
i=0からi<n+1とj=0からj<m+1の範囲で2重ループし、なるべく打ち切り条件を入れたが、
TLE28個、WA6個となった。

# Main
int[] aryb,arywに読み込み、昇順にソートし、
long[] sumb,sumwに大きい方からの累積和を計算する。
まず白の位置をint idxとしてi=0からi<Math.min(n+1,m+1)までsumb[i]+sumw[i]が最大値となる位置を探す。
白の位置をidxに固定し、i=idxからi<n+1までsumb[i]+sumw[idx]が最大値となる組み合わせを探す。

# Main\_fix
int[] aryb,arywに読み込み、昇順にソートし、
long[] sumb,sumwに大きい方からの累積和を計算する。
sumwは最大値より小さくなる場合、前回の値をコピーする。
i=0からi<n+1までsumb\[i\]+sumw\[i\]の最大値を探すが、sumw\[i\]がi>mの場合はsumw\[m\]とする。
