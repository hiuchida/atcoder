# Main
n,kを読み込み、ans=0を用意する。
n==1の場合ans=k
n==2の場合ans=modmul(k, k-1)
k==2の場合ans=0　（n>2は、3色必要。本来k<=2だが、k==1はk-1を掛けるときに0となっていた）
それ以外の場合ans=modmul(k, k-1)、a2=modpow(k-2, n-2, M)、ans=modmul(ans, a2)
ansを出力する。
AC 73ms
