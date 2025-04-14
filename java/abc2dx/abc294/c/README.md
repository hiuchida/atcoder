# Main
n,mを読み込み、int[] aa,abに読み込む。
ans=1、i=0、j=0、StringBuilder sba,sbbを用意する。
i<n && j<mの間ループし、
aa[i]<ab[j]の場合、sbaにansを追加し、ans++、i++を入れる。
それ以外の場合、sbbにansを追加し、ans++、j++を入れる。

i<nの間ループし、sbaにansを追加し、ans++、i++を入れる。

j<mの間ループし、sbbにansを追加し、ans++、j++を入れる。

sba,sbbを出力する。
