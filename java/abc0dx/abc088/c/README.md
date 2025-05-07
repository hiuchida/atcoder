# Main
int[][] mapに読み込む。
d1 = map[0][1] - map[0][0]、d2 = map[1][1] - map[1][0]、d3 = map[2][1] - map[2][0]を入れ、
d1 != d2 || d2 != d3の場合ng。

d1 = map[0][2] - map[0][1]、d2 = map[1][2] - map[1][1]、d3 = map[2][2] - map[2][1]を入れ、
d1 != d2 || d2 != d3の場合ng。

d1 = map[1][0] - map[0][0]、d2 = map[1][1] - map[0][1]、d3 = map[1][2] - map[0][2]を入れ、
d1 != d2 || d2 != d3の場合ng。

d1 = map[2][0] - map[1][0]、d2 = map[2][1] - map[1][1]、d3 = map[2][2] - map[1][2]を入れ、
d1 != d2 || d2 != d3の場合ng。
それ以外はok。
AC 72ms

# Main2
int[][] deltaを用意し、iを0から3回ループし、
delta[0][i] = map[i][1] - map[i][0]
delta[1][i] = map[i][2] - map[i][1]
delta[2][i] = map[1][i] - map[0][i]
delta[3][i] = map[2][i] - map[1][i]を入れる。

jを0から4回ループし、iを0から2回ループし、
delta[j][i] != delta[j][i + 1]の場合ng。
AC 71ms

# Main\_fix
書き直し。
a,bが100以下なので総当たりで探索する。

a1を0から100までループし、
b1=ary[0][0]-a1を入れ、b1<0の場合中断する。
a2=ary[1][0]-b1、a3=ary[2][0]-b1を入れ、a2<0 || a3<0の場合スキップする。
b2=ary[0][1]-a1、b3=ary[0][2]-a1を入れ、b2<0 || b3<0の場合スキップする。
a2+b2!=ary[1][1]やa3+b2!=ary[2][1]やa2+b3!=ary[1][2]やa3+b3!=ary[2][2]の場合スキップする。
それ以外はok。
ループ終了したらng。
AC 76ms

解説を読むと、a,bが非負という条件はないので、たまたまACとなったのか。。。

# Main\_fix\_TestData
Main\_fixを検証するため、0以上10以下の値をary\[i\]\[j\]に入れて、
10^9通りを比較する。

# Main\_fix2
書き直し。
a1=0のみで、a2,a3,b1,b2,b3を計算し、矛盾があればng。一致すればok。
AC 77ms

