# Main
nを読み込み、ans=1を用意する。
n回ループし、aを読み込み、ans=calc_lcm(ans, a)を入れる。
ループ終了したら、ansを出力する。

calc_lcmの中で、
gcd=calc_gcd(a, b)を入れ、
a/gcd\*bを返す。

calc_gcdの中で、
a>=1 && b>=1の間ループし、
a>bの場合a%=bを入れ、それ以外はb%=aを入れる。
ループ終了したら、a>=1の場合a、それ以外はbを返す。
