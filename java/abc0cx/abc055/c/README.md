# Main01
n,mを読み込み、ans=0を用意する。
n\*2<=mの場合、ans+=n、m-=n\*2、n=0を入れる。
c=m/4を用意し、n+=c、m-=c\*2、ans+=c、n-=c、m-=c\*2を入れる。
ansを出力する。
WA3個。

# Main02
Main01を元に、
n\*2<=mの場合、ans+=n、m-=n\*2、n=0を入れる。
を
n<=m/2の間ループし、c=n/10を用意し、ans+=c、n-=c、m-=c\*2を入れる。
に修正。
WA3個。TLE5個。

# Main03
Main02を元に、
n<=m/2の間ループし、c=n/10を用意し、ans+=c、n-=c、m-=c\*2を入れる。
を
n>0 && n<=m/2の間ループし、c=max(1, n/10)を用意し、ans+=c、n-=c、m-=c\*2を入れる。
に修正。
WA3個。

# Main04
Main03を元に、
ans+=c、n-=c、m-=c\*2を入れる。
を
n>0 && n<=m/2の間ループし、c=max(1, n/10)を用意し、ans+=c、n-=c、m-=c\*2を入れる。
に修正。
WA3個。

# Main
n,mを読み込み、ans=0を用意する。
n\*2>mの場合、m/2を出力して、終了する。
それ以外の場合、ans+=n、m-=n*2、ans+=m/4を入れ、ansを出力する。

# Main_TestData
1<=n,m<=1000で比較したら、すぐにMain01とMainの差異が見つかった。
以下のようにn\*2>mのとき、n:m=1:2で計算しないで、m/4個をn1個に変換する計算しかしていない。
2 2
