# Main01
nを読み込み、int\[\] aryに読み込み、ソートする。
min=ary[0]、ans=0を用意し、iをn-1から0までループする。
ary[i]>minの間ループし、bdone=trueを用意し、
ary[i]%2==0 && ary[i]/2>=minならばary[i]/=2、ans++、bdone=falseを入れる。
ary[i]%3==0 && ary[i]/3>=minならばary[i]/=3、ans++、bdone=falseを入れる。
bdoneならば中断する。
ary[i]!=minならばans=-1を入れる。
iループ終了したら、ansを出力する。
WA16個。

# Main02
Main01を元に、
gcd=0を用意し、int\[\] aryを読み込むときに、gcd=gcd(gcd, ary[i])を更新する。
iを0からn回ループし、ary[i]>gcdの間ループし、
ary[i]%2==0 && ary[i]/2>=gcdならばary[i]/=2
ary[i]%3==0 && ary[i]/3>=gcdならばary[i]/=3
ary[i]!=gcdならばans=-1を入れる。
WA15個。

# Main
Main02を元に、
min=INT\_MAX、max=0を用意し、
ループの先頭で、ary[i]/=gcdを入れる。
ary[i]%2==0の間ループしary[i]/=2
ary[i]%3==0の間ループしary[i]/=3
min=min(min, ary[i])、max=max(max, ary[i])を更新する。
ループ終了したら、min!=maxのときans=-1を入れる。

