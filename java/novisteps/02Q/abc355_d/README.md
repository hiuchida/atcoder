# Main01
int[] al,arに入れて、ペアを無視して、ソートする。
ar[n]に番兵を入れる。
ilを0からn-1までループし、
ir=0からar[ir]<al[il]を満たす限りir++する。
ansをn*(n-1)/2として、irを引く。
WA18個。

# Main02
long ans=n*(n-1)/2;をlong ans=n*(n-1)/2L;と、longで計算する。
WA18個。

# Main
long ans=n*(n-1)/2L;をlong ans=(long)n*(n-1)/2L;と、longで計算する。

# Main\_fix
static long calc(int a, int b) { //abc295_d,abc350_d,abc355_d,typical90_084: aCbの組み合わせ数

