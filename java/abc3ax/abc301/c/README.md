# Main
s,tを読み込み、char[] as,atに展開し、cs=0、ct=0を用意する。
iを0からn回ループし、
as[i]=='@'の場合、cs++し、as[i]='~'を入れる。（ソートしたとき'z'より後になる）
at[i]=='@'の場合、ct++し、at[i]='~'を入れる。
as,atをソートする。
i=0、j=0を用意し、i<n && j<nの間ループし、
as[i]==at[j]の場合、i++、j++する。
as[i]<at[j]の場合、
ct>0 && tbl.indexOf(as[i])>=0の場合、ct--、i++する。それ以外はng。
as[i]>at[j]の場合、
cs>0 && tbl.indexOf(at[j])>=0の場合、cs--、j++する。それ以外はng。

ループ終了して、
i<n && as[i]=='~'の間i++する。
j<n && at[j]=='~'の間j++する。

i==n && j==nの場合ok、それ以外はng。
