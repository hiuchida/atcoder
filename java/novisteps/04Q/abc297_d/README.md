# Main
a,bを読み込み、ans=0を用意する。
a!=bの間ループし、
a>bの場合、c=(a-1)/b、a-=b*c、ans+=cを入れる。
a<bの場合、c=(b-1)/a、b-=a*c、ans+=cを入れる。

ansを出力する。

c=a/bとした場合、a=9,b=3のときにc=3、a=0、ans+=3となり、
次にa<bとなりc=b/aで0で割ることになる。
このため、(a-1)/bでc=8/3=2、a=9-6=3とし、a==bとなり正常に終了する。
