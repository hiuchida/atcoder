# Main
nを読み込み、int[] aryに読み込む。
TreeMap<Integer,Bean> mapを用意し、iを0からn回ループし、mapに(i+1, new Bean(ary[i]))を追加する。
qを読み込み、def=-1を用意する。
q回ループし、cを読み込む。
c==1の場合、xを読み込み、def=xを更新し、mapをクリアする。
c==2の場合、i,xを読み込み、b=map.get(i)を入れ、
b==nullならばb=new Bean(def+x)を作成し、mapに(i, b)を追加する。
それ以外ならばbにxを加算する。
c==3の場合、iを読み込み、b=map.get(i)を入れ、
b==nullならばdefを出力し、
それ以外ならばb.vを出力する。
