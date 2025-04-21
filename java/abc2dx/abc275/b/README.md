# Main01
a,b,c,d,e,fを読み込み、すべてMで割った余りに更新する。
ab=a\*bを計算し、Mで割った余りに更新する。
abc=ab\*cを計算し、Mで割った余りに更新する。
de=d\*eを計算し、Mで割った余りに更新する。
def=de\*fを計算し、Mで割った余りに更新する。
ans=abc-defを計算し、Mで割った余りに更新する。
ansを出力する。
WA8個。

# Main
Main01を元に、ansを計算した際に負の数の場合にMを加える。

# Main\_fix
ModFunc20250423適用

# Main\_TestData
Main01の再現条件の調査ツール
1000 1000 1000 121 121 121

