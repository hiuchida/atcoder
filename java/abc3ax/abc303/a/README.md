# Main
n,s,tを読み込み、char[] as,atに展開する。
iを0からn回ループし、
as[i]==at[i]、as[i]=='1' && at[i]=='l'、as[i]=='l' && at[i]=='1'
as[i]=='0' && at[i]=='o'、as[i]=='o' && at[i]=='0'の場合スキップする。
それ以外はng。
ループ終了したらok。
