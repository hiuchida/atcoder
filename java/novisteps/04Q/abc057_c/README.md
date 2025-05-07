# Main
nを読み込み、ans=INT\_MAXを用意する。
aを1からa\*a<=nまでループし、n%a!=0ならばスキップする。
b=n/a、x=max(calc(a), calc(b))を入れ、ansにxの最小値を更新する。
ループ終了したら、ansを出力する。

calcの中で、ans=0を用意し、
n>0の間ループし、ans++、n/=10を入れ、
ループ終了したら、ansを返す。
