# Main
nを読み込み、pr=new Prime(N+1)、ans=0を用意する。
pr.listの要素ccをループし、c=cc*ccを入れ、c>nの場合中断する。
pr.listの要素bをループし、b>=ccの場合中断する。
ab=c*bを入れ、ab>nの場合中断する。
pr.listの要素aaをループし、aa>=bの場合中断する。
abc=ab*aaを入れ、abc>n/aaの場合中断する。
ans++をカウントする。
ansを出力する。

# Main\_fix
PrimeMini20250425を適用

