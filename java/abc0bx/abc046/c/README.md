# Main
nを読み込み、tt = 0、aa = 0を用意し、
iを0からn回ループし、
i == 0の場合、tt = t、aa = aを入れ、スキップする。
tn = tt/t、an = aa/a、nn = Math.max(tn, an)を入れ、
無限ループし、
nt = t\*nn、na = a\*nnを入れ、
nt >= tt && na >= aaの場合、tt = nt、aa = naを入れ、中断する。
nn++を加える。
tt+aaを出力する。
AC 125ms

# Main\_fix
書き直す。

nを読み込み、at=0、aa=0を用意し、
iを0からn回ループし、t,aを読み込み、
at==0の場合、at=t、aa=aを入れる。
それ以外は、nt=at/t、na=aa/a、nm=Math.max(nt, na)を入れ、
jをnmから無限ループし、tt=j\*t、ta=j\*aを入れ、
tt>=at && ta>=aaの場合、at=tt、aa=taを入れ、中断する。

at+aaを出力する。
AC 119ms

