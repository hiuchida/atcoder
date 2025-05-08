# Main
n,sを読み込み、char[] aryに展開する。
ans=calc(n, 2)、MyArray mao、maxを用意し、

iを0からn回ループし、
ary[i]=='o'の場合、jをiからj<nまでループし、ary[j]!='o'の場合中断する。
mao.add(j-i)を追加し、i=j-1を入れる。
ary[i]=='x'の場合、jをiからj<nまでループし、ary[j]!='x'の場合中断する。
max.add(j-i)を追加し、i=j-1を入れる。

mao.toArray()の要素vをループし、ans-=calc(v, 2)を入れる。
max.toArray()の要素vをループし、ans-=calc(v, 2)を入れる。
ansを出力する。
AC 200ms
