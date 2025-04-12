# Main01
nを読み込み、int[] aa,abに読み込み、ソートする。
s1=aa[0]+ab[0]、s2=max(aa[0], ab[1])、s3=max(aa[1], ab[0])を入れ、
min(Math.min(s1, s2), s3)を出力する。
WA5個。

# Main02
Main01を元に、
int[] aa,abでは、ソート後に従業員が区別できないので、Point[] aa,abに変更する。
aa[i]=new Point(sc.nextInt(), i)、ab[i]=new Point(sc.nextInt(), i)に読み込み、ソートする。
aa[0].ed!=ab[0].edの場合、max(aa[0].st, ab[0].st)を出力する。
それ以外の場合、s1=aa[0].st+ab[0].st、s2=max(aa[0].st, ab[1].st)、s3=max(aa[1].st, ab[0].st)を入れ、
min(Math.min(s1, s2), s3)を出力する。
WA16個。

# Main
Main02を元に、
aa[0].ed!=ab[0].edの場合に、ansを出力していなかった。

