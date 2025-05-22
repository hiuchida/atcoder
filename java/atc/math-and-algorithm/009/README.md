# Main01
n,sを読み込み、int[] aryに読み込む。
iを0から2^n回ループし、sum=0を用意する。
jを0からn回ループし、mask=1 << jを用意し、(i&mask)>0の場合、sum+=ary[j]を加える。
sum==sの場合、ok。
ループ終了したら、ng。
WA5個TLE4個。

# Main
本文 2.4.6 項の回答のため、TLEは許容するが、WAはコードの問題。
n=60のとき、2^60はint型で扱えないため、int iとint maskをlong iとlong maskに変更する。
また、1 << nは(int)1 << nの定数のため2^31までしか正しい値でないので、1L << nに変更する。
TLE9個。（Subtask2の中で2個ACしたのは、Yesが見つかると終了するため）

