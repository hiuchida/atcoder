# Main01
a,b,c,d,e,fを読み込み、maxD = 0.0、maxW = 0、maxS = 0を用意する。
aaを0からf / 100まで+aループし、
bbを0から無限ループで+bし、weight(aa, bb, 0, 0, f)の場合中断する。
ccを0から無限ループで+cし、weight(aa, bb, cc, 0, f)の場合中断する。
ddを0から無限ループで+dし、weight(aa, bb, cc, dd, f)の場合中断する。
density(aa, bb, cc, dd, e)の場合中断する。
weight = 100 * aa + 100 * bb + cc + ddを入れ、weight == 0の場合中断する。
density = 100.0 * (cc + dd) / weightを入れ、
maxD < densityの場合、maxD = density、maxW = weight、maxS = cc + ddを入れる。
maxW,maxSを出力する。

weightの中で、
w = 100 * a + 100 * b + c + dを入れ、w > fを返す。

densityの中で、
w = (a + b) * e、s = c + dを入れ、s > wを返す。
WA1個。

# Main02
Main01を元に、
aaをna、bbをnb、ccをnc、ddをndに変更。
weight == 0の場合中断する。　を　スキップする。
WA1個。

# Main03
Main02を元に、
naを無限ループに変更。weight(na, 0, 0, 0, f)の場合中断する。
WA1個。

# Main
Main03を元に、砂糖0、水xの時に、maxWが0のまま更新されない。
maxD = 0.0
を
maxD = -1.0
に変更。
AC 89ms

# Main\_fix
書き直す。
a,b,c,d,e,fを読み込み、max1=0、max2=0を用意する。
iを0からi\*100\*a<=fまでループし、w1=i\*100\*aを用意する。
jを0からw1+j\*100\*b<=fまでループし、w2=w1+j\*100\*bを用意する。
gを0からw2+g\*c<=fまでループし、s1=g\*c、w3=w2+s1を用意する。
hを0からw3+h\*d<=fまでループし、s2=s1+h\*d、w4=w2+s2を用意する。
e\*w2/100>=s2の場合、max2==0やmax2\*w4<s2\*max1の場合、max1=w4、max2=s2を入れる。（max2/max1<s2/w4から変形）
ループ終了したら、max1,max2を出力する。
AC 99ms
