# Main
まず右へ移動するように、sx>txの場合は、(sx,sy)と(tx,ty)を交換する。
sx+syが偶数ならば、sx++進める。
tx+tyが奇数ならば、tx--戻す。
abs(ty-sy)をansに加え、この分だけsxを進める。
まだsx<txの場合、tx+tyが奇数なので、sx+syも奇数に調整する。
(tx-sx)/2をansに加える。
