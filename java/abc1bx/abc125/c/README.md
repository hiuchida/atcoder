# Main
nを読み込み、int[] aryに読み込む。
long[] lt、gcd=0を用意し、iを0からn-1までループし、
lt[i+1]にgcdとary[i]の最大公約数を入れ、gcdを更新する。
long[] rt、gcd=0を用意し、iをn-1から0までループし、
rt[i]にgcdとary[i]の最大公約数を入れ、gcdを更新する。
ans=0を用意し、iを0からn-1までループし、
lt\[i\]とrt\[i+1\]の最大公約数の最大数をansに更新する。
ループが終了したら、ansを出力する。

lt\[i\]の左端に0が入り、rt\[i\]の右端に0が入る。
ary: a, b, c, d
lt\[\]: 0, a, gcd(a,b), gcd(a,b,c), gcd(a,b,c,d)
rt\[\]: gcd(a,b,c,d), gcd(b,c,d), gcd(c,d), d, 0

gcd(lt\[i\], rt\[i+1\])
i=0: gcd(0, gcd(b,c,d))
i=1: gcd(a, gcd(c,d))
i=2: gcd(gcd(a,b), d)
i=3: gcd(gcd(a,b,c), 0)
