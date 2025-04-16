# Main
s,tを読み込み、char[] as,atに展開する。
int[] idx、ngcnt=0、bng=falseを用意する。
iを0からn回ループし、idx[i]=s.length()-1-(n-1-i)を入れ、
!check(as[idx[i]], at[i])の場合、bng=true、ngcnt++を更新する。
ループ終了したら、!bngの場合ok、それ以外はng。

iを1からnまでループし、j=i-1、bng=falseを入れ、
!check(as[idx[j]], at[j])の場合、bng=trueを入れる。（入れ替え前の状態）
idx[j]=jを入れ、
!check(as[idx[j]], at[j])かつ!bngの場合、ngcnt++を更新する。（okからngに変化）
それ以外でbngの場合、ngcnt--を更新する。（ngからokに変化）
ngcnt==0の場合ok、それ以外はng。

checkの中で、c1=='?' || c2=='?'の場合true。
c1==c2を返す。
