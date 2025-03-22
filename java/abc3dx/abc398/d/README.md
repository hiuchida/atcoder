# Main
TreeSet<Point> set,int dy=0,dx=0を用意する。
時刻0のときのset.add(new Point(-dy, -dx));を追加する。
iを0からn-1までループし、sの位置iの文字の方角によって、dy,dxを計算する。
原点を補正したset.add(new Point(-dy, -dx));を追加する。
補正した座標set.contains(new Point(r-dy, c-dx))でチェックし、'0','1'を追加する。
