# Main
s,String[] as,atを読み込む。
iを0からn-1までループする。
s=as[i]として、jを0からn-1までループし、i!=jのときsとas[j]やat[j]と一致するか判定しbs=trueする。
同様にt=at[i]として、i!=jのときtとas[j]やat[j]と一致するか判定しbt=trueする。
bsとbtがどちらもtrueならばng。
ループが終了したらok。
