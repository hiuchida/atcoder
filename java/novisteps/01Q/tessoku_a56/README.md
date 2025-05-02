# Main
n,q,sを読み込み、StringHash shを用意する。
q回ループし、a,b,c,dを読み込み、
h1=sh.hashValue(a, b)、h2=sh.hashValue(c, d)を入れ、
h1==h2の場合ok、それ以外はng。
